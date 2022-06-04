package com.web_app.cefion.rest.admin_panel;

import com.web_app.cefion.model.user.User;
import com.web_app.cefion.model.user.Role;
import com.web_app.cefion.repository.UserRepository;
import com.web_app.cefion.rest.DTO.DTOController;
import com.web_app.cefion.rest.DTO.ModelUpdate;
import com.web_app.cefion.rest.DTO.UserDTO;
import com.web_app.cefion.rest.DTO.RegistrationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@PreAuthorize("hasAuthority('manage:users')")
public class RolesRestController {
    private final UserRepository userRepository;

    @Autowired
    public RolesRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/get_by_role/{role}")
    public List<UserDTO> get_by_role(@PathVariable Role role) {
        return userRepository.findUsersByRole(role).map(users -> users.stream()
                .map(DTOController::user_to_DTO).collect(Collectors.toList()))
                .orElseThrow(() -> new NullPointerException("List is empty"));
    }

    @GetMapping("/get_by_username/{username}")
    public UserDTO get_by_username(@PathVariable String username) {
        return userRepository.findByUsername(username).map(DTOController::user_to_DTO)
                .orElseThrow(() -> new NullPointerException("List is empty"));
    }

    @PostMapping("/create")
    public String create(@RequestBody @Valid RegistrationRequestDTO request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Username already exist.";
        }
        User user = DTOController.DTO_to_user(request);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return "User save error.";
        }
        return "Success";
    }

    @PutMapping("/update/{id}")
    public String update(@RequestBody UserDTO userDTO, @PathVariable Integer id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.save(ModelUpdate.update_user(user, userDTO));
                    return "User was updated.";
                })
                .orElse("User doesn't exist.");
    }

    @PostMapping("/get_username_status")
    public boolean getUsernameStatus(@RequestBody String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}

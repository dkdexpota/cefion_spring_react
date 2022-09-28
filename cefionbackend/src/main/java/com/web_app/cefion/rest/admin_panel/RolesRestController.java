package com.web_app.cefion.rest.admin_panel;

import com.web_app.cefion.model.user.Permission;
import com.web_app.cefion.model.user.User;
import com.web_app.cefion.model.user.RoleController;
import com.web_app.cefion.repository.UserRepository;
import com.web_app.cefion.rest.DTO.*;
import com.web_app.cefion.rest.DTO.user.RegistrationRequestDTO;
import com.web_app.cefion.rest.DTO.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")

public class RolesRestController {
    private final UserRepository userRepository;

    @Autowired
    public RolesRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/role/{role}")
    public List<UserDTO> get_by_role(@PathVariable String role) {
        ArrayList<Permission> perms = new ArrayList<>(Arrays.asList(Permission.values()));
        int id = 4 - perms.indexOf(Permission.valueOf(role.toUpperCase()));
        List<Integer> perm = new ArrayList<>();
        for (int i = (1 << id) + 32; i < 64; i++)
            if (Integer.toBinaryString(i).charAt(5 - id) == '1') perm.add(i - 32);
        return userRepository.findAllByRoleIn(perm).map(users -> users.stream()
                .map(DTOController::user_to_DTO).collect(Collectors.toList()))
                .orElseThrow(() -> new NullPointerException("List is empty"));
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/page/{id}/{username}")
    public List<UserDTO> get_page_by_username(@PathVariable Integer id, @PathVariable String username) {
        id--;
        return userRepository.findUsersByUsernameStartingWith(username, PageRequest.of(id, 20)).stream().map(DTOController::user_to_DTO).toList();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/page/{id}")
    public List<UserDTO> get_page(@PathVariable Integer id) {
        id--;
        return userRepository.findAll(PageRequest.of(id, 20)).stream().map(DTOController::user_to_DTO).toList();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/username/{username}")
    public UserDTO get_by_username(@PathVariable String username) {
        return userRepository.findByUsername(username).map(DTOController::user_to_DTO)
                .orElseThrow(() -> new NullPointerException("List is empty"));
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/create")
    public String create(@RequestBody @Valid RegistrationRequestDTO request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Username already exist.";
        }
        User user = DTOController.registrationDTO_to_user(request);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return "User save error.";
        }
        return "Success";
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/update/{id}")
    public String update(List<String> roles, @PathVariable Integer id) {
        return userRepository.findById(id)
                .map(user -> {
                    if (user.getRole() < 16) {
                        user.setRole(RoleController.setAuthorities(roles));
                        userRepository.save(user);
                        return "User was updated.";
                    } else {
                        return "Access denied";
                    }
                })
                .orElse("User doesn't exist.");
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/get_username_status")
    public boolean getUsernameStatus(@RequestBody String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @PreAuthorize("hasAuthority('super_admin')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            userRepository.deleteById(id);
            return "Success";
        } catch (Exception e) {
            return "User doesn't exist";
        }

    }
}

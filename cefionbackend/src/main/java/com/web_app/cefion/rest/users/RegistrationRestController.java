package com.web_app.cefion.rest.users;


import com.web_app.cefion.model.user.User;
import com.web_app.cefion.repository.UserRepository;
import com.web_app.cefion.rest.DTO.DTOController;
import com.web_app.cefion.rest.DTO.user.RegistrationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/reg")
public class RegistrationRestController {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
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
}

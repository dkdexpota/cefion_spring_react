package com.web_app.cefion.rest.admin_panel.landing;

import com.web_app.cefion.repository.InNumberRepository;
import com.web_app.cefion.rest.DTO.*;
import com.web_app.cefion.rest.DTO.landing.InNumberDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/landing/numbers")

public class InNumberRestController {
    private final InNumberRepository inNumberRepository;

    public InNumberRestController(InNumberRepository inNumberRepository) {
        this.inNumberRepository = inNumberRepository;
    }

    @GetMapping
    public List<InNumberDTO> getAll() {
        return inNumberRepository.findAll().stream().map(DTOController::inNumber_to_DTO).toList();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public String create(@RequestBody InNumberDTO inNumberDTO) {
        try {
            inNumberRepository.save(DTOController.DTO_to_inNumber(inNumberDTO));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Success";
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping
    public String update(@RequestBody InNumberDTO inNumberDTO) {
        return inNumberRepository.findById(inNumberDTO.getId())
                .map(inNumber -> {
                    inNumberRepository.save(ModelUpdate.update_inNumber(inNumber, inNumberDTO));
                    return "Number was update";
                }).orElse("Number update error");
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        inNumberRepository.deleteById(id);
        return "Success";
    }
}

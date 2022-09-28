package com.web_app.cefion.rest.admin_panel.landing;

import com.web_app.cefion.repository.PurposeRepository;
import com.web_app.cefion.repository.RoadMapRepository;
import com.web_app.cefion.rest.DTO.*;
import com.web_app.cefion.rest.DTO.landing.FullRoadMapDTO;
import com.web_app.cefion.rest.DTO.landing.PurposeDTO;
import com.web_app.cefion.rest.DTO.landing.RoadMapDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/landing/road_map")

public class RoadMapRestController {
    private final PurposeRepository purposeRepository;
    private final RoadMapRepository roadMapRepository;

    public RoadMapRestController(PurposeRepository purposeRepository, RoadMapRepository roadMapRepository) {
        this.purposeRepository = purposeRepository;
        this.roadMapRepository = roadMapRepository;
    }

    @GetMapping
    public FullRoadMapDTO getAll() {
        List<PurposeDTO> purposesDTO = purposeRepository.findAll().stream().map(DTOController::purpose_to_DTO).toList();
        RoadMapDTO roadMapDTO = roadMapRepository.findById(1).map(DTOController::roadMap_to_DTO).orElse(new RoadMapDTO());
        FullRoadMapDTO fullRoadMapDTO = new FullRoadMapDTO();
        fullRoadMapDTO.setRoadMapDTO(roadMapDTO);
        fullRoadMapDTO.setPurposesDTO(purposesDTO);
        return fullRoadMapDTO;
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/purpose")
    public String createPurpose(@RequestBody PurposeDTO purposeDTO) {
        try {
            purposeRepository.save(DTOController.DTO_to_purpose(purposeDTO));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Success";
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping
    public String updateRoadMap(@RequestBody RoadMapDTO roadMapDTO) {
        return roadMapRepository.findById(1)
                .map(roadMap -> {
                    roadMapRepository.save(ModelUpdate.update_roadMap(roadMap, roadMapDTO));
                    return "Road Map was update";
                }).orElse("Road Map update error");
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/purpose")
    public String updatePurpose(@RequestBody PurposeDTO purposeDTO) {
        return purposeRepository.findById(purposeDTO.getId())
                .map(purpose -> {
                    purposeRepository.save(ModelUpdate.update_purpose(purpose, purposeDTO));
                    return "Chapter was update";
                }).orElse("Chapter update error");
    }


    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/purpose/{id}")
    public String deletePurpose(@PathVariable Integer id) {
        purposeRepository.deleteById(id);
        return "Success";
    }
}

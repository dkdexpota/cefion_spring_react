package com.web_app.cefion.rest.DTO.landing;

import lombok.Data;

import java.util.List;

@Data
public class AboutDTO {
    private AboutDescriptionDTO aboutDescriptionDTO;
    private List<PersonDTO> personsDTO;
}

package com.web_app.cefion.rest.DTO;

import lombok.Data;

import java.util.List;

@Data
public class FullRoadMapDTO {
    private RoadMapDTO roadMapDTO;
    private List<PurposeDTO> purposesDTO;
}

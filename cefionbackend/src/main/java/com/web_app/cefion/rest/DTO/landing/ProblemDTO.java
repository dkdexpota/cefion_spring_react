package com.web_app.cefion.rest.DTO.landing;

import lombok.Data;

@Data
public class ProblemDTO {
    private int id;
    private int chapterId;
    private String question;
    private String answer;
}

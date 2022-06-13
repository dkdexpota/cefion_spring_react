package com.web_app.cefion.rest.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NewsCardDTO {
    private int id = 0;
    private Date date = new Date();
    private String title;
    private String type;
    private List<String> hashtags;
    private String author;
}

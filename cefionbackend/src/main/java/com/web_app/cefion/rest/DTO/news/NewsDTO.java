package com.web_app.cefion.rest.DTO.news;

import lombok.Data;

import java.io.File;
import java.util.Date;
import java.util.List;

@Data
public class NewsDTO {
    private int id = 0;
    private Date date = new Date();
    private String titleRU;
    private String titleEU;
    private String descriptionRU;
    private String descriptionEU;
    private String author;
    private String type;
    private List<String> hashtags;
}

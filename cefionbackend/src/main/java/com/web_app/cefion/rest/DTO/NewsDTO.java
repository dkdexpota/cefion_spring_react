package com.web_app.cefion.rest.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class NewsDTO {
    private Date date;
    private int img_id;
    private String ru_title;
    private String eu_title;
    private String ru_body;
    private String eu_body;
    private String author;
}

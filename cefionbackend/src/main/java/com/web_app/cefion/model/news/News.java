package com.web_app.cefion.model.news;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "NEWS")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DATE")
    Date date;

    @Column(name = "IMG_ID")
    int img_id;

    @Column(name = "RU_TITLE")
    String ru_title;
    @Column(name = "EU_TITLE")
    String eu_title;

    @Column(name = "RU_BODY")
    String ru_body;
    @Column(name = "EU_BODY")
    String eu_body;

    @Column(name = "AUTHOR")
    String author;
}

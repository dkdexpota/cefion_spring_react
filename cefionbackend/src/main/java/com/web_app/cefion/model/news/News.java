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
    private Date date;

    @Column(name = "IMG_NAME")
    private String img_name;

    @Column(name = "TYPE")
    private Type type;

    @Column(name = "HASHTAGS")
    private String hashtags;

    @Column(name = "TITLE_RU")
    private String titleRU;
    @Column(name = "TITLE_EU")
    private String titleEU;

    @Column(name = "DESCRIPTION_RU")
    private String DescriptionRU;
    @Column(name = "DESCRIPTION_EU")
    private String DescriptionEU;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "STATUS")
    private Status status;
}

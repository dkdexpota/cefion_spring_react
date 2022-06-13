package com.web_app.cefion.model.landing;

import com.web_app.cefion.model.news.Status;
import com.web_app.cefion.model.news.Type;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "MEDIA")
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "IMG_NAME")
    private String img_name;

    @Column(name = "TITLE_RU")
    private String titleRU;
    @Column(name = "TITLE_EU")
    private String titleEU;

    @Column(name = "DESCRIPTION_RU")
    private String DescriptionRU;
    @Column(name = "DESCRIPTION_EU")
    private String DescriptionEU;

    @Column(name = "SOURCE")
    private String source;
}

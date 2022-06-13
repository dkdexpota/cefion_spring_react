package com.web_app.cefion.model.landing;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PURPOSE")
public class Purpose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITLE_RU")
    private String titleRU;
    @Column(name = "TITLE_EU")
    private String titleEU;

    @Column(name = "DESCRIPTION_RU")
    private String DescriptionRU;
    @Column(name = "DESCRIPTION_EU")
    private String DescriptionEU;
}

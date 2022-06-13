package com.web_app.cefion.model.landing;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FULL_NAME_RU")
    private String fullNameRu;
    @Column(name = "FULL_NAME_EU")
    private String fullNameEu;

    @Column(name = "POSITION_RU")
    private String positionRu;
    @Column(name = "POSITION_EU")
    private String positionEu;

    @Column(name = "IMG_NAME")
    private String img_name;
}

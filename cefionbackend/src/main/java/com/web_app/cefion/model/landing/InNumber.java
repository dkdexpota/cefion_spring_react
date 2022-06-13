package com.web_app.cefion.model.landing;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "IN_NUMBER")
public class InNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NUM")
    private String num;

    @Column(name = "DESCRIPTION_RU")
    private String DescriptionRU;
    @Column(name = "DESCRIPTION_EU")
    private String DescriptionEU;
}

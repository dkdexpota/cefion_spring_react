package com.web_app.cefion.model.landing;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ROAD_MAP")
public class RoadMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DESCRIPTION_RU")
    private String DescriptionRU;
    @Column(name = "DESCRIPTION_EU")
    private String DescriptionEU;
}

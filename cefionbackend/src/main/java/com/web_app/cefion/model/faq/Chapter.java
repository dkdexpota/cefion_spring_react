package com.web_app.cefion.model.faq;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "CHAPTER")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CHAPTER_NAME")
    private String chapterName;

    @OneToMany(mappedBy = "Chapter", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Problem> problems;

}

package com.web_app.cefion.model.faq;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PROBLEM")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "ANSWER")
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Chapter chapter;
}

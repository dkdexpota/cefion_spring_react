package com.web_app.cefion.model.faq;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "CHAPTER")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Problem> problems;

    public void addProblem(Problem problem) {
        this.problems.add(problem);
        problem.setChapter(this);
    }

    public void removeProblem(Problem problem) {
        this.problems.remove(problem);
        problem.setChapter(null);
    }
}

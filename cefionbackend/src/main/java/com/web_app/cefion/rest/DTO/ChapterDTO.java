package com.web_app.cefion.rest.DTO;

import com.web_app.cefion.model.faq.Problem;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
public class ChapterDTO {
    private int id;
    private String name;
}

package com.web_app.cefion.rest.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class MediaDTO {
    private int id;
    Date date = new Date();
    String titleRU;
    String titleEU;
    String DescriptionRU;
    String DescriptionEU;
    String source;
}

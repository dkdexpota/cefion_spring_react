package com.web_app.cefion.rest.DTO;

import com.web_app.cefion.model.faq.Chapter;
import com.web_app.cefion.model.faq.Problem;
import com.web_app.cefion.model.landing.*;
import com.web_app.cefion.model.news.News;
import com.web_app.cefion.model.user.User;
import com.web_app.cefion.rest.DTO.landing.*;
import com.web_app.cefion.rest.DTO.news.NewsDTO;
import com.web_app.cefion.rest.DTO.user.UserDTO;

import java.util.Date;

public abstract class ModelUpdate {

    public static News update_news(News news, NewsDTO newsDTO, String imgName) {
        news.setDate(new Date());
        news.setImg_name(imgName);
        news.setHashtags(String.join(" ", newsDTO.getHashtags()));
        news.setDescriptionEU(newsDTO.getDescriptionEU());
        news.setDescriptionRU(newsDTO.getDescriptionRU());
        news.setTitleRU(newsDTO.getTitleRU());
        news.setTitleEU(newsDTO.getTitleEU());
        return news;
    }

    public static InNumber update_inNumber(InNumber inNumber, InNumberDTO inNumberDTO) {
        inNumber.setNum(inNumberDTO.getNum());
        inNumber.setDescriptionRU(inNumberDTO.getDescriptionRU());
        inNumber.setDescriptionEU(inNumberDTO.getDescriptionEU());
        return inNumber;
    }

    public static RoadMap update_roadMap(RoadMap roadMap, RoadMapDTO roadMapDTO) {
        roadMap.setDescriptionEU(roadMapDTO.getDescriptionEU());
        roadMap.setDescriptionRU(roadMapDTO.getDescriptionRU());
        return roadMap;
    }

    public static Purpose update_purpose(Purpose purpose, PurposeDTO purposeDTO) {
        purpose.setTitleRU(purposeDTO.getTitleRU());
        purpose.setTitleEU(purposeDTO.getTitleEU());
        purpose.setDescriptionRU(purposeDTO.getDescriptionRU());
        purpose.setDescriptionEU(purposeDTO.getDescriptionEU());
        return purpose;
    }

    public static About update_about(About about, AboutDescriptionDTO aboutDescriptionDTO) {
        about.setDescriptionEU(aboutDescriptionDTO.getDescriptionEU());
        about.setDescriptionRU(aboutDescriptionDTO.getDescriptionRU());
        return about;
    }

    public static Person update_person(Person person, PersonDTO personDTO, String imgName) {
        person.setPositionRu(personDTO.getPositionRu());
        person.setPositionEu(personDTO.getPositionEu());
        person.setFullNameRu(personDTO.getFullNameRu());
        person.setFullNameEu(personDTO.getFullNameEu());
        person.setImg_name(imgName);
        return person;
    }

    public static Media update_media(Media media, MediaDTO mediaDTO, String imgName) {
        media.setImg_name(imgName);
        media.setDate(new Date());
        media.setSource(mediaDTO.getSource());
        media.setDescriptionEU(mediaDTO.getDescriptionEU());
        media.setDescriptionRU(mediaDTO.getDescriptionRU());
        media.setTitleRU(mediaDTO.getTitleRU());
        media.setTitleEU(mediaDTO.getTitleEU());
        return media;
    }

    public static Chapter update_chapter(Chapter chapter, ChapterDTO chapterDTO) {
        chapter.setName(chapterDTO.getName());
        return chapter;
    }

    public static Problem update_problem(Problem problem, ProblemDTO problemDTO) {
        problem.setQuestion(problemDTO.getQuestion());
        problem.setAnswer(problemDTO.getAnswer());
        return problem;
    }
}

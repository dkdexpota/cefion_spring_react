package com.web_app.cefion.rest.DTO;

import com.web_app.cefion.model.faq.Chapter;
import com.web_app.cefion.model.faq.Problem;
import com.web_app.cefion.model.landing.*;
import com.web_app.cefion.model.news.News;
import com.web_app.cefion.model.news.Price;
import com.web_app.cefion.model.news.Type;
import com.web_app.cefion.model.user.RoleController;
import com.web_app.cefion.model.user.User;
import com.web_app.cefion.model.user.Status;
import com.web_app.cefion.rest.DTO.landing.*;
import com.web_app.cefion.rest.DTO.news.NewsDTO;
import com.web_app.cefion.rest.DTO.news.PriceDTO;
import com.web_app.cefion.rest.DTO.user.RegistrationRequestDTO;
import com.web_app.cefion.rest.DTO.user.UserDTO;
import org.json.simple.JSONObject;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;

public abstract class DTOController {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public static InNumberDTO inNumber_to_DTO(InNumber inNumber) {
        InNumberDTO inNumberDTO = new InNumberDTO();
        inNumberDTO.setId(inNumber.getId());
        inNumberDTO.setNum(inNumber.getNum());
        inNumberDTO.setDescriptionEU(inNumber.getDescriptionEU());
        inNumberDTO.setDescriptionRU(inNumber.getDescriptionRU());
        return inNumberDTO;
    }

    public static PriceDTO price_to_DTO(Price price) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setTotal_volume(price.getTotal_volume());
        priceDTO.setMarket_cap(price.getMarket_cap());
        priceDTO.setMarket_cap_change_24h_in_currency(price.getMarket_cap_change_24h_in_currency());
        priceDTO.setMarket_cap_change_percentage_24h(price.getMarket_cap_change_percentage_24h());
        priceDTO.setCurrent_price_btc(price.getCurrent_price_btc());
        priceDTO.setCurrent_price_usd(price.getCurrent_price_usd());
        priceDTO.setPrice_change_percentage_1h_in_currency(price.getPrice_change_percentage_1h_in_currency());
        priceDTO.setPrice_change_percentage_24h_in_currency(price.getPrice_change_percentage_24h_in_currency());
        priceDTO.setPrice_change_percentage_7d_in_currency(price.getPrice_change_percentage_7d_in_currency());
        priceDTO.setPrice_change_percentage_30d_in_currency(price.getPrice_change_percentage_30d_in_currency());
        return priceDTO;
    }

    public static InNumber DTO_to_inNumber(InNumberDTO inNumberDTO) {
        InNumber inNumber = new InNumber();
        inNumber.setId(inNumberDTO.getId());
        inNumber.setNum(inNumberDTO.getNum());
        inNumber.setDescriptionEU(inNumberDTO.getDescriptionEU());
        inNumber.setDescriptionRU(inNumberDTO.getDescriptionRU());
        return inNumber;
    }

    public static PersonDTO person_to_DTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFullNameEu(person.getFullNameEu());
        personDTO.setFullNameRu(person.getFullNameRu());
        personDTO.setPositionEu(person.getPositionEu());
        personDTO.setPositionRu(person.getPositionRu());
        return personDTO;
    }

    public static Purpose DTO_to_purpose(PurposeDTO purposeDTO) {
        Purpose purpose = new Purpose();
        purpose.setTitleEU(purposeDTO.getTitleEU());
        purpose.setTitleRU(purposeDTO.getTitleRU());
        purpose.setDescriptionEU(purposeDTO.getDescriptionEU());
        purpose.setDescriptionRU(purposeDTO.getDescriptionRU());
        return purpose;
    }

    public static PurposeDTO purpose_to_DTO(Purpose purpose) {
        PurposeDTO purposeDTO = new PurposeDTO();
        purposeDTO.setId(purpose.getId());
        purposeDTO.setTitleEU(purpose.getTitleEU());
        purposeDTO.setTitleRU(purpose.getTitleRU());
        purposeDTO.setDescriptionEU(purpose.getDescriptionEU());
        purposeDTO.setDescriptionRU(purpose.getDescriptionRU());
        return purposeDTO;
    }

    public static RoadMapDTO roadMap_to_DTO(RoadMap roadMap) {
        RoadMapDTO roadMapDTO = new RoadMapDTO();
        roadMapDTO.setDescriptionEU(roadMap.getDescriptionEU());
        roadMapDTO.setDescriptionRU(roadMap.getDescriptionRU());
        return roadMapDTO;
    }

    public static Person DTO_to_person(PersonDTO personDTO, String imgName) {
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setFullNameEu(personDTO.getFullNameEu());
        person.setFullNameRu(personDTO.getFullNameRu());
        person.setPositionEu(personDTO.getPositionEu());
        person.setPositionRu(personDTO.getPositionRu());
        person.setImg_name(imgName);
        return person;
    }

    public static AboutDescriptionDTO aboutDescription_to_DTO(About about) {
        AboutDescriptionDTO aboutDescriptionDTO = new AboutDescriptionDTO();
        aboutDescriptionDTO.setDescriptionEU(about.getDescriptionEU());
        aboutDescriptionDTO.setDescriptionRU(about.getDescriptionRU());
        return aboutDescriptionDTO;
    }

    public static News DTO_to_news(NewsDTO newsDTO, String imgName) {
        News news = new News();
        news.setImg_name(imgName);
        news.setDate(new Date());
        news.setAuthor(newsDTO.getAuthor());
        news.setHashtags(String.join(" ", newsDTO.getHashtags()));
        news.setType(Type.valueOf(newsDTO.getType()));
        news.setTitleEU(newsDTO.getTitleEU());
        news.setTitleRU(newsDTO.getTitleRU());
        news.setDescriptionEU(newsDTO.getDescriptionEU());
        news.setDescriptionRU(newsDTO.getDescriptionRU());
        return news;
    }

    public static NewsDTO news_to_DTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setDate(news.getDate());
        newsDTO.setId(news.getId());
        newsDTO.setAuthor(news.getAuthor());
        newsDTO.setTitleEU(news.getTitleEU());
        newsDTO.setTitleRU(news.getTitleRU());
        newsDTO.setDescriptionEU(news.getDescriptionEU());
        newsDTO.setDescriptionRU(news.getDescriptionRU());
        newsDTO.setType(news.getType().toString());
        newsDTO.setHashtags(Arrays.asList(news.getHashtags().split(" ")));
        return newsDTO;
    }

    public static MediaDTO media_to_DTO(Media media) {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setId(media.getId());
        mediaDTO.setDate(media.getDate());
        mediaDTO.setDescriptionEU(media.getDescriptionEU());
        mediaDTO.setDescriptionRU(media.getDescriptionRU());
        mediaDTO.setTitleEU(media.getTitleEU());
        mediaDTO.setTitleRU(media.getTitleRU());
        mediaDTO.setSource(media.getSource());
        return mediaDTO;
    }

    public static Media DTO_to_media(MediaDTO mediaDTO, String img_name) {
        Media media = new Media();
        media.setDate(new Date());
        media.setDescriptionEU(mediaDTO.getDescriptionEU());
        media.setDescriptionRU(mediaDTO.getDescriptionRU());
        media.setTitleEU(mediaDTO.getTitleEU());
        media.setTitleRU(mediaDTO.getTitleRU());
        media.setSource(mediaDTO.getSource());
        media.setImg_name(img_name);
        return media;
    }

    public static User registrationDTO_to_user(RegistrationRequestDTO requestDTO) {
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(encoder.encode(requestDTO.getPassword()));
        user.setTagName(requestDTO.getTagName());
        user.setRole(RoleController.setAuthorities(requestDTO.getRoles()));
        user.setStatus(Status.ACTIVE);
        return user;
    }

    public static UserDTO user_to_DTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setTagName(user.getTagName());
        userDTO.setRoles(RoleController.getRoles(user.getRole()).stream().map(r -> r.toString().toLowerCase()).toList());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }

    public static Chapter DTO_to_chapter(ChapterDTO chapterDTO) {
        Chapter chapter = new Chapter();
        chapter.setName(chapterDTO.getName());
        return chapter;
    }

    public static ChapterDTO chapter_to_DTO(Chapter chapter) {
        ChapterDTO chapterDTO = new ChapterDTO();
        chapterDTO.setName(chapter.getName());
        chapter.setProblems(null);
        return chapterDTO;
    }

    public static Problem DTO_to_problem(ProblemDTO problemDTO) {
        Problem problem = new Problem();
        problem.setAnswer(problemDTO.getAnswer());
        problem.setQuestion(problemDTO.getQuestion());
        return problem;
    }

    public static ProblemDTO problem_to_DTO(Problem problem) {
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setAnswer(problem.getAnswer());
        problemDTO.setChapterId(problem.getChapter().getId());
        problemDTO.setQuestion(problem.getQuestion());
        problemDTO.setId(problem.getId());
        return problemDTO;
    }

}

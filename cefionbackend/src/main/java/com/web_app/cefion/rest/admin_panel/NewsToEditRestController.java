package com.web_app.cefion.rest.admin_panel;

import com.web_app.cefion.model.news.Status;
import com.web_app.cefion.repository.NewsRepository;
import com.web_app.cefion.rest.DTO.news.NewsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/news_edit")
@PreAuthorize("hasAuthority('copywriter')")
public class NewsToEditRestController {

    private final NewsRepository newsRepository;
    private final String imgPath;
    public NewsToEditRestController(NewsRepository newsRepository, @Value("${img.path.news}") String imgPath) {
        this.newsRepository = newsRepository;
        this.imgPath = imgPath;
    }

    @GetMapping
    public List<NewsDTO> getAll() {
        return ANews.getAll(Status.EDIT, newsRepository);
    }

    @GetMapping("/{type}/page/{id}")
    public List<NewsDTO> getPage(@PathVariable String type, @PathVariable Integer id) {
        return ANews.getPage(Status.EDIT, type, id, newsRepository);
    }

    @GetMapping("/{id}")
    public NewsDTO getOneNews(@PathVariable Integer id) {
        return ANews.getOneNews(Status.EDIT, id, newsRepository);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return ANews.delete(Status.EDIT, id, imgPath, newsRepository);
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<Resource> getImg(@PathVariable Integer id) {
        return ANews.getImg(Status.EDIT, id, imgPath, newsRepository);
    }

    @PutMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String update(@RequestPart("file") MultipartFile file, @RequestPart("news") NewsDTO newsDTO) {
        return ANews.update(Status.EDIT, file, newsDTO, imgPath, newsRepository);
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String create(@RequestPart("file") MultipartFile file, @RequestPart("news") NewsDTO newsDTO) {
        return ANews.create(Status.EDIT, file, newsDTO, imgPath, newsRepository);
    }
}

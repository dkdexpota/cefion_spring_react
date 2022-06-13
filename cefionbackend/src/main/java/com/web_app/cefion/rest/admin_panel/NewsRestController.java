package com.web_app.cefion.rest.admin_panel;

import com.web_app.cefion.model.news.Status;
import com.web_app.cefion.repository.NewsRepository;
import com.web_app.cefion.rest.DTO.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/news")
@PreAuthorize("hasAuthority('edit:posts')")
public class NewsRestController {
    private final String imgPath;
    private final NewsRepository newsRepository;

    public NewsRestController(NewsRepository newsRepository, @Value("${img.path.news}") String imgPath) {
        this.newsRepository = newsRepository;
        this.imgPath = imgPath;
    }

    @GetMapping
    public List<NewsDTO> getAll() {
        return ANews.getAll(Status.PUBLIC, newsRepository);
    }

    @GetMapping("/{type}/page/{id}")
    public List<NewsDTO> getPage(@PathVariable String type, @PathVariable Integer id) {
        return ANews.getPage(Status.PUBLIC, type, id, newsRepository);
    }

    @GetMapping("/{id}")
    public NewsDTO getOneNews(@PathVariable Integer id) {
        return ANews.getOneNews(Status.PUBLIC, id, newsRepository);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return ANews.delete(Status.PUBLIC, id, imgPath, newsRepository);
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<Resource> getImg(@PathVariable Integer id) {
        return ANews.getImg(Status.PUBLIC, id, imgPath, newsRepository);
    }

    @PutMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String update(@RequestPart("file") MultipartFile file, @RequestPart("news") NewsDTO newsDTO) {
        return ANews.update(Status.PUBLIC, file, newsDTO, imgPath, newsRepository);
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String create(@RequestPart("file") MultipartFile file, @RequestPart("news") NewsDTO newsDTO) {
        String err = ANews.update(Status.PUBLIC, file, newsDTO, imgPath, newsRepository);
        if (Objects.equals(err, "")) {
            ANews.changeStatus(Status.EDIT, Status.PUBLIC, newsDTO.getId(), newsRepository);
            return "Success";
        }
        return err;
    }
}

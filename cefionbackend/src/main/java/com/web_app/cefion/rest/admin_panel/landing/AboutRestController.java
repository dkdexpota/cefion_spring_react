package com.web_app.cefion.rest.admin_panel.landing;

import com.web_app.cefion.model.landing.Person;
import com.web_app.cefion.repository.AboutRepository;
import com.web_app.cefion.repository.PersonRepository;
import com.web_app.cefion.rest.DTO.*;
import com.web_app.cefion.rest.DTO.landing.AboutDTO;
import com.web_app.cefion.rest.DTO.landing.AboutDescriptionDTO;
import com.web_app.cefion.rest.DTO.landing.PersonDTO;
import com.web_app.cefion.rest.admin_panel.ANews;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/landing/about")

public class AboutRestController {
    private final PersonRepository personRepository;
    private final AboutRepository aboutRepository;
    private final String imgPath;

    public AboutRestController(PersonRepository personRepository, AboutRepository aboutRepository, @Value("${img.path.person}") String imgPath) {
        this.personRepository = personRepository;
        this.aboutRepository = aboutRepository;
        this.imgPath = imgPath;
    }

    @GetMapping
    public AboutDTO getAll() {
        List<PersonDTO> persons = personRepository.findAll().stream().map(DTOController::person_to_DTO).toList();
        AboutDescriptionDTO aboutDescriptionDTO = aboutRepository.findById(1).map(DTOController::aboutDescription_to_DTO).orElse(new AboutDescriptionDTO());
        AboutDTO aboutDTO = new AboutDTO();
        aboutDTO.setAboutDescriptionDTO(aboutDescriptionDTO);
        aboutDTO.setPersonsDTO(persons);
        return aboutDTO;
    }

    @GetMapping("/person/img/{id}")
    public ResponseEntity<Resource> getImg(@PathVariable Integer id) {
        try {
            Person person = personRepository.findById(id).orElseThrow(() -> new NullPointerException("News doesn't exists"));
            File file = new File(imgPath + person.getImg_name());
            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (NullPointerException e) {
            return ResponseEntity.ok().body(null);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping(name = "/person", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String createPerson(@RequestPart("file") MultipartFile file, @RequestPart("person") PersonDTO personDTO) {
        String imgName = ANews.saveFile(file, imgPath);
        if(imgName != null) {
            try {
                personRepository.save(DTOController.DTO_to_person(personDTO, imgName));
            } catch (Exception e) {
                return "Media save error.";
            }
            return "Success";
        } else {
            return "File save error.";
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping(name = "/person", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String updatePerson(@RequestPart("file") MultipartFile file, @RequestPart("person") PersonDTO personDTO) {
        String imgName = ANews.saveFile(file, imgPath);
        if (imgName != null) {
            return personRepository.findById(personDTO.getId())
                    .map(person -> {
                        String error = "";
                        try {
                            new File(imgPath + person.getImg_name()).delete();
                        } catch (Exception e) {
                            error = "Old file doesn't exist.";
                        }

                        try {
                            personRepository.save(ModelUpdate.update_person(person, personDTO, imgName));
                        } catch (Exception e) {
                            return "Person update error. " + error;
                        }
                        return "Person was updated. " + error;
                    })
                    .orElse("Person doesn't exist");
        } else {
            return "File save error.";
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping
    public String updateAbout(@RequestBody AboutDescriptionDTO aboutDescriptionDTO) {
        return aboutRepository.findById(1)
                .map(about -> {
                    aboutRepository.save(ModelUpdate.update_about(about, aboutDescriptionDTO));
                    return "About was update";
                }).orElse("About update error");
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/person/{id}")
    public String deletePerson(@PathVariable Integer id) {
        return personRepository.findById(id)
                .map(person -> {
                    String err = "Success";
                    try {
                        boolean st = new File(imgPath + person.getImg_name()).delete();
                        if(!st) {
                            err = "Old file doesn't exist.";
                        }
                    } catch (Exception e) {
                        err = "File error.";
                    }
                    personRepository.deleteById(id);
                    return err;
                }).orElse("Person doesn't exist");
    }
}

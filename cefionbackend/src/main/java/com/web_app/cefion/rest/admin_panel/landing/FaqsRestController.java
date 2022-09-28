package com.web_app.cefion.rest.admin_panel.landing;

import com.web_app.cefion.model.faq.Chapter;
import com.web_app.cefion.model.faq.Problem;
import com.web_app.cefion.repository.ChapterRepository;
import com.web_app.cefion.repository.ProblemRepository;
import com.web_app.cefion.rest.DTO.landing.ChapterDTO;
import com.web_app.cefion.rest.DTO.DTOController;
import com.web_app.cefion.rest.DTO.ModelUpdate;
import com.web_app.cefion.rest.DTO.landing.ProblemDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/landing/faqs")
public class FaqsRestController {
    private final ChapterRepository chapterRepository;
    private final ProblemRepository problemRepository;

    public FaqsRestController(ChapterRepository chapterRepository, ProblemRepository problemRepository) {
        this.chapterRepository = chapterRepository;
        this.problemRepository = problemRepository;
    }

    @GetMapping("/problem/{id}")
    @Transactional
    public List<ProblemDTO> get_faqs(@PathVariable Integer id) {
        try {
            return chapterRepository
                    .findById(id)
                    .orElseThrow(NullPointerException::new)
                    .getProblems()
                    .stream()
                    .map(DTOController::problem_to_DTO)
                    .collect(Collectors.toList());
        } catch (NullPointerException ignored) {
            return null;
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/problem/{id}")
    public String update_faqs(@RequestBody ProblemDTO problemDTO, @PathVariable Integer id) {
        return problemRepository.findById(id)
                .map(problem -> {
                    problemRepository.save(ModelUpdate.update_problem(problem, problemDTO));
                    return "Problem was update";
                }).orElse("Problem update error");
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/problem")
    public String create_faqs(@RequestBody ProblemDTO problemDTO) {
        try {
            Problem problem = DTOController.DTO_to_problem(problemDTO);
            Chapter chapter = chapterRepository.findById(problemDTO.getChapterId()).orElseThrow(NullPointerException::new);
            chapter.addProblem(problem);
            try {
                chapterRepository.save(chapter);
            } catch (Exception e) {
                return e.getMessage();
            }
        } catch (NullPointerException e) {
            return "Chapter doesn't exist";
        }
        return "Success";
    }

    @PreAuthorize("hasAuthority('admin')")
    @Transactional
    @DeleteMapping("/problem/{id}")
    public String delete_faqs(@PathVariable Integer id) {
        problemRepository.deleteById(id);
        return "Success";
    }

    @GetMapping
    public List<ChapterDTO> get_chapters() {
        return (chapterRepository.findAll().stream().map(DTOController::chapter_to_DTO).collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public String update_chapter(@RequestBody ChapterDTO chapterDTO, @PathVariable Integer id) {
        return chapterRepository.findById(id)
                .map(chapter -> {
                    chapterRepository.save(ModelUpdate.update_chapter(chapter, chapterDTO));
                    return "Chapter was update";
                }).orElse("Chapter update error");
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public String create_chapter(@RequestBody ChapterDTO chapterDTO) {
        Chapter chapter = DTOController.DTO_to_chapter(chapterDTO);
        try {
            chapterRepository.save(chapter);
        } catch (Exception e) {
            return "Chapter save error.";
        }
        return "Success";
    }
}

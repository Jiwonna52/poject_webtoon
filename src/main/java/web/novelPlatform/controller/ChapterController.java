package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.novelPlatform.controller.form.ChapterForm;
import web.novelPlatform.entity.chapter.Chapter;
import web.novelPlatform.service.ChapterService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChapterController {
    private final ChapterService chapterService;

    @GetMapping(value = "/chapters/new")
    public String createForm(Model model){
        model.addAttribute("chapterForm", new ChapterForm());
        return "chapters/createChapterForm";
    }

    @PostMapping("/chapter/new")
    public String create(ChapterForm form){
        Chapter chapter = new Chapter();
        chapter.setTitle(form.getTitle());
        chapterService.createChapter(chapter);

        return "redirect:/chapters";
    }


    //해당 소설의 챕터를 보여주는 리스트
    @GetMapping(value = "/chapters")
    public String list(Model model){
        List<Chapter> chapters = chapterService.findChapters();
        model.addAttribute("chapters", chapters);

        return "chapter/chapterList";
    }
}

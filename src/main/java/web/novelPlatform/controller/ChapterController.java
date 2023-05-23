package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.novelPlatform.controller.form.ChapterForm;
import web.novelPlatform.entity.Novel;
import web.novelPlatform.entity.chapters.Chapter;
import web.novelPlatform.service.ChapterService;
import web.novelPlatform.service.NovelService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChapterController {
    private final ChapterService chapterService;
    private final NovelService novelService;

    @GetMapping(value = "/chapters/new/{novelId}")
    public String createForm(Model model){
        model.addAttribute("chapterForm", new ChapterForm());

        return "chapters/createChapterForm";
    }

    @PostMapping("/chapters/new/{novelId}")
    public String create(@PathVariable("novelId") Long novelId, ChapterForm form){

        Chapter chapter = new Chapter();
        Novel novel = (Novel) novelService.findOne(novelId);
        chapter.setTitle(form.getTitle());
        chapter.setNovel(novel);

        chapterService.createChapter(chapter);

        return "redirect:/{novelId}/chapters";
    }

    //값을 보여주는 것이니까 Get을 써야 한다.
    @GetMapping(value = "/{novelId}/chapters")
    public String chapterList(@PathVariable("novelId") Long novelId, Model model){
        List<Chapter> chapters = chapterService.findNovelChapters(novelId);
        model.addAttribute("chapters", chapters);

        return "chapters/chapterList";
    }

}

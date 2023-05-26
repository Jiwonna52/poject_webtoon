package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.novelPlatform.controller.form.ContentForm;
import web.novelPlatform.entity.chapters.Chapter;
import web.novelPlatform.entity.chapters.Content;
import web.novelPlatform.service.ChapterService;
import web.novelPlatform.service.ContentService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;
    private final ChapterService chapterService;

    @GetMapping(value = "/contents/{chapterId}/new")
    public String createContent(Model model){
        model.addAttribute("contentForm", new ContentForm());

        return "contents/createContentForm";
    }

    @PostMapping("/contents/{chapterId}/new")
    public String create(@PathVariable("chapterId") Long chapterId, ContentForm form){
        Content content = new Content();
        Chapter chapter = (Chapter) chapterService.findOne(chapterId);
        content.setContents(form.getContents());
        content.setChapter(chapter);

        return "/home";

    }

    @GetMapping(value = "/{chapterId}/contents")
    public String contentList(@PathVariable("chapterId") Long chapterId, Model model){
        List<Content> contents = contentService.findContentsByChapterId(chapterId);
        return "/home";
    }

}

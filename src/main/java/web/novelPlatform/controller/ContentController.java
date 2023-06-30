package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.novelPlatform.controller.form.ContentForm;
import web.novelPlatform.entity.Novel;
import web.novelPlatform.entity.Content;
import web.novelPlatform.service.ContentService;
import web.novelPlatform.service.NovelService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;
    private final NovelService novelService;

    @GetMapping(value = "/{novelId}/contents/new")
    public String createContent(Model model){
        model.addAttribute("contentForm", new ContentForm());

        return "contents/createContentForm";
    }

    @PostMapping("/{novelId}/contents/new")
    public String create(@PathVariable("novelId") Long novelId,  ContentForm form){
        Content content = new Content();
        Novel novel = (Novel) novelService.findOne(novelId);
        content.setTitle(form.getTitle());
        content.setContents(form.getContents());
        content.setNovel(novel);
        contentService.createContent(content);

        return "redirect:/{novelId}/contents";

    }

    @GetMapping(value = "/{novelId}/contents")
    public String contentList(@PathVariable("novelId") Long novelId, Model model){
        List<Content> contents = contentService.findContentByNovelId(novelId);
        model.addAttribute("contents", contents);
        return "/contents/contentList";
    }

}

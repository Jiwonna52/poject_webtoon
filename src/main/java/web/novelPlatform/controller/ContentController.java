package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.novelPlatform.controller.form.CommentForm;
import web.novelPlatform.controller.form.ContentForm;
import web.novelPlatform.entity.Comment;
import web.novelPlatform.entity.Novel;
import web.novelPlatform.entity.Content;
import web.novelPlatform.service.ContentService;
import web.novelPlatform.service.NovelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;
    private final NovelService novelService;

    //새로운 회차 등록
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

        return "redirect:/novels/{novelId}/contents/update";

    }

    //회차 목록
    @GetMapping(value = "/{novelId}/contents")
    public String contentList(@PathVariable("novelId") Long novelId, Model model){
        List<Content> contents = contentService.findContentByNovelId(novelId);
        Novel novel = novelService.findOne(novelId);
        model.addAttribute("novel", novel);
        model.addAttribute("contents", contents);
        return "/contents/contentList";
    }

    @GetMapping(value = "/novels/{novelId}/view/{contentId}")
    public String contentDetailView(@PathVariable("novelId") Long novelId, @PathVariable("contentId") Long contentId, Model model){
        //소설을 가지고 온다.
        Novel novel = novelService.findOne(novelId);
        //해당 소설의 원하는 소제목과 내용을 가지고 온다.
        List<Content> contentList = contentService.findOne(novelId, contentId);
        Content content = contentList.get(0);
        System.out.println(content.getTitle());
        System.out.println(content.getContents());
        model.addAttribute("content", content);

        return "/contents/contentDetail";

    }

    @GetMapping(value = "/novels/{novelId}/contents/update")
    public String contentUpdate(@PathVariable("novelId") Long novelId, Model model){
        List<Content> contents = contentService.findContentByNovelId(novelId);
        Novel novel = novelService.findOne(novelId);
        model.addAttribute("novel", novel);
        model.addAttribute("contents", contents);

        return "/contents/contentUpdate";
    }

    /*
    내일 추가한당...
    @GetMapping(value ="/novels/{novelId}/contents/{contentId}/update")
    public String contentDetailUpdate(){

    }*/




/*
    @GetMapping(value="/novels/{novelId}/view/{contentId}/comment")
    public String createComment(Model model){
        model.addAttribute("commentForm", new CommentForm());

        return "contents/contentDetail";
    }

    @PostMapping(value="/novels/{novelId}/view/{contentId}/comment")
    public String createCommentForm(@PathVariable("contentId") Long contentId, @PathVariable("novelId") Long novelId, CommentForm form){
        Comment comment = new Comment();
        List<Content> content = contentService.findOne(novelId, contentId);
        comment.setComment(form.getComment());
        comment.setMember(form.getMember());
        comment.setContent(content.get(0));

        return"redirect:/novels/{novelId}/view/{contentId}";
    }*/

    /*
    @GetMapping(value ="/{novelId}/contents/{contentId}/update")
    public String updateForm(@PathVariable("novelId") Long novelId, @PathVariable("contentId") Long contentId, Model model){
        List<Content> contentList = contentService.findContentByNovelId(novelId);
        Content content = contentList.contains()
    }*/

}

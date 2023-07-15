package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.novelPlatform.controller.form.CommentForm;
import web.novelPlatform.entity.Comment;
import web.novelPlatform.entity.Content;
import web.novelPlatform.service.ContentService;

import java.util.List;


public class CommentController {
/*
    private final ContentService contentService;

    //댓글 등록
    @GetMapping(value="/novels/{novelId}/view/{contentId}/comment")
    public String createComment(Model model){
        model.addAttribute("commentForm", new CommentForm());

        return "contents/contentDetail";
    }

    @PostMapping(value="/novels/{novelId}/view/{contentId}/comment")
    public String create(@PathVariable("contentId") Long contentId, @PathVariable("novelId") Long novelId, CommentForm form){
        Comment comment = new Comment();
        List<Content> content = contentService.findOne(novelId, contentId);
        comment.setComment(form.getComment());
        comment.setMember(form.getMember());
        comment.setContent(content.get(0));
}
     */


}

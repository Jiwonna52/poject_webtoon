package web.novelPlatform.controller.form;


import lombok.Getter;
import lombok.Setter;
import web.novelPlatform.entity.Content;
import web.novelPlatform.entity.Member;

@Getter
@Setter
public class CommentForm {
    private String comment;
    private Member member;
    private Content content;
}

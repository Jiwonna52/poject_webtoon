package web.novelPlatform.controller.form;

import lombok.Getter;
import lombok.Setter;
import web.novelPlatform.entity.chapters.Chapter;

@Getter
@Setter
public class ContentForm {

    private String contents;
    private Chapter chapter;
}

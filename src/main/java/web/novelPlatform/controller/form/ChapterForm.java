package web.novelPlatform.controller.form;

import lombok.Getter;
import lombok.Setter;
import web.novelPlatform.entity.Novel;

@Getter
@Setter
public class ChapterForm {

    private String title;
    private Novel novel;

}

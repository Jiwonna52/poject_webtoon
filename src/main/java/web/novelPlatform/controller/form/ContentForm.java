package web.novelPlatform.controller.form;

import lombok.Getter;
import lombok.Setter;
import web.novelPlatform.entity.Webtoon;

@Getter
@Setter
public class ContentForm {
    private String title;
    private String contents;
    private String path;
    private Webtoon webtoon;

    private Long id;
}

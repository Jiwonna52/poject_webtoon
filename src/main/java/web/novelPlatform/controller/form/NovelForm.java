package web.novelPlatform.controller.form;

import lombok.Getter;
import lombok.Setter;
import web.novelPlatform.entity.Genre;
import web.novelPlatform.entity.SerialState;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class NovelForm {

    @NotEmpty(message = "제목은 필수입니다.")
    private String title;
    private String introduce;

    private Genre genre;

    private SerialState serialState;

    private Long id;


}

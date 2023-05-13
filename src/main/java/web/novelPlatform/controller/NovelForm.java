package web.novelPlatform.controller;

import lombok.Getter;
import lombok.Setter;
import web.novelPlatform.entity.Genre;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class NovelForm {

    @NotEmpty(message = "제목은 필수입니다.")
    private String title;
    private String introduce;

}

package web.novelPlatform.controller.form;

import lombok.Getter;
import lombok.Setter;
import web.novelPlatform.entity.Novel;

//form을 따로 만드는 이유 -> 화면 객체와 서비스 객체를 분리하기 위해서
@Getter
@Setter
public class ChapterForm {

    private String title;
    private Novel novel;

}

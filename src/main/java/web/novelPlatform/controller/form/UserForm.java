package web.novelPlatform.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserForm {
    @NotEmpty(message = "필명은 필수 입니다")
    private String name;
    private String email;
}

package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.novelPlatform.controller.form.NovelForm;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    @GetMapping(value = "/logIn") //get은 form화면을 열어보고
    public String createForm(Model model){
        return "members/logIn";
    }
}

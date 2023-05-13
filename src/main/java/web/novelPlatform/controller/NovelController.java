package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Address;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.novelPlatform.entity.Novel;
import web.novelPlatform.service.NovelService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NovelController {

    private final NovelService novelService;

    //get방식으로 html에서 novels/new로 오면 return해서 novels/createNovelForm이 열리는 것이다.
    @GetMapping(value = "/novels/new") //get은 form화면을 열어보고
    public String createForm(Model model){
        model.addAttribute("novelForm", new NovelForm());

        return "novels/createNovelForm";
    }

    @PostMapping("/novels/new") //데이터를 실제 등록
    //NovelForm이 넘어온다.
    //참고 꼭 넣어야 하는 게 있다면 form에다가 @Valid를 붙여준다.
    public String create(NovelForm form){
        Novel novel = new Novel();
        novel.setTitle(form.getTitle());
        novel.setIntroduce(form.getIntroduce());

        novelService.createNovel(novel);

        return "redirect:/";
    }

    @RequestMapping("/novel")
    public String novel(){
        return "novel";
    }
}

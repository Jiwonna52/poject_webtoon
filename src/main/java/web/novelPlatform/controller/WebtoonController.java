package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.novelPlatform.controller.form.NovelForm;
import web.novelPlatform.entity.Genre;
import web.novelPlatform.entity.Novel;
import web.novelPlatform.entity.SerialState;
import web.novelPlatform.service.NovelService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String create(@ModelAttribute("form") NovelForm form) {
        Novel novel = new Novel();
        novel.setTitle(form.getTitle());
        novel.setIntroduce(form.getIntroduce());
        novel.setGenre(form.getGenre());
        novel.setSerialState(SerialState.Live);
        novelService.createNovel(novel);

        //소설 목록으로 넘어간다.
        return "redirect:/myNovels";
    }

    //enum을 셀렉트 박스로
    @ModelAttribute("Genre")
    public Genre[] genres(){
        return Genre.values();
    }

    //소설의 목록을 보여주는
    @GetMapping(value = "/myNovels")
    public String novelList(Model model){
        List<Novel> novels = novelService.findNovels();
        model.addAttribute("novels", novels);

        return "novels/novelList";
    }


    @GetMapping(value="/novels/{novelId}/update")
    public String updateNovelForm(@PathVariable("novelId") Long novelId, Model model){
        Novel novel = novelService.findOne(novelId);
        NovelForm form = new NovelForm();
        form.setId(novelId);
        form.setTitle(novel.getTitle());
        form.setIntroduce(novel.getIntroduce());
        form.setSerialState(novel.getSerialState());
        form.setGenre(novel.getGenre());

        model.addAttribute("novelForm", form);

        return "novels/novelUpdate";
    }


    @PostMapping(value="/novels/{novelId}/update")
    public String updateNovel(@PathVariable("novelId") Long novelId, @ModelAttribute("form") NovelForm form){
        novelService.updateNovel(novelId, form.getTitle(), form.getIntroduce(), form.getSerialState(), form.getGenre());
        return "redirect:/myNovels";
    }

    //@RequestMapping(value = "/novels/{novelId}/delete", method = RequestMethod.DELETE)
    @PostMapping(value = "/novels/{novelId}/delete")
    public String deleteNovel(@PathVariable("novelId") Long novelId){
        novelService.deleteNovel(novelId);
        return "redirect:/myNovels";
    }





}

package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import web.novelPlatform.entity.Content;
import web.novelPlatform.entity.Webtoon;
import web.novelPlatform.service.ContentService;
import web.novelPlatform.service.WebtoonService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;
    private final WebtoonService webtoonService;


    //회차 목록
    @GetMapping(value = "/{webtoonId}/contents")
    public String contentList(@PathVariable("webtoonId") Long webtoonId, Model model){
        List<Content> contents = contentService.findContentByNovelId(webtoonId);
        Webtoon webtoon = webtoonService.findOne(webtoonId);
        model.addAttribute("webtoon", webtoon);
        model.addAttribute("contents", contents);
        //return "/contents/contentList";
        return "contents/contentList";
    }

    @GetMapping(value = "/webtoon/{webtoonId}/view/{contentId}")
    public String contentDetailView(@PathVariable("webtoonId") Long webtoonId, @PathVariable("contentId") Long contentId, Model model){
        Webtoon webtoon = webtoonService.findOne(webtoonId);
        List<Content> contentList = contentService.findOne(webtoonId, contentId);
        Content content = contentList.get(0);
        model.addAttribute("content", content);

        //return "/contents/contentDetail";
        return "contents/contentDetail";

    }



}

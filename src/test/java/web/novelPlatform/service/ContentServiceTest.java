package web.novelPlatform.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.entity.Genre;
import web.novelPlatform.entity.Novel;
import web.novelPlatform.entity.SerialState;
import web.novelPlatform.entity.Member;
import web.novelPlatform.entity.Content;
import web.novelPlatform.repository.ContentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ContentServiceTest {

    @Autowired
    memberService memberService;
    @Autowired
    NovelService novelService;
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    ContentService contentService;
    @Test
    public void 내용등록(){
        //Given
        Member member = new Member();
        member.setName("A");
        Long userID = memberService.join(member);

        Novel novel = new Novel();
        novel.setTitle("악녀한테 너무해!");
        novel.setGenre(Genre.RomanceFantasy);
        novel.setSerialState(SerialState.Live);
        novel.setMember(member);
        Long novelId = novelService.createNovel(novel);


        //when
        Content content = new Content();
        content.setContents("1화 내용");
        Long contentId = contentService.createContent(content);

        //Then

    }
    @Test
    public void 내용검색() throws Exception{
        //Given
        //새로운 멤버 등록
        Member member = new Member();
        member.setName("A");
        Long userID = memberService.join(member);

        //새로운 소설 등록
        Novel novel = new Novel();
        novel.setTitle("악녀한테 너무해!");
        novel.setIntroduce("어쩌구저쩌구");
        novel.setGenre(Genre.RomanceFantasy);
        novel.setSerialState(SerialState.Live);
        novel.setMember(member);
        Long novelId = novelService.createNovel(novel);

        //새로운 회차 등록
        Content content = new Content();
        content.setTitle("1화");
        content.setNovel(novel);
        content.setContents("내용");
        Long contentId = contentService.createContent(content);

        Content content2 = new Content();
        content2.setTitle("2화");
        content2.setNovel(novel);
        content2.setContents("2화 내용");
        Long contentId2 = contentService.createContent(content2);

        //when
        List<Content> contentList = new ArrayList<>();
        contentList = contentService.findOne(novelId, contentId2);
        System.out.println(contentList.get(0).getTitle());
    }
/*
    @Test
    public void 챕터삭제() throws Exception{
        //Given
        Member member = new Member();
        member.setName("A");
        Long userID = memberService.join(member);

        Novel novel = new Novel();
        novel.setTitle("악녀한테 너무해!");
        novel.setGenre(Genre.RomanceFantasy);
        novel.setSerialState(SerialState.Live);
        novel.setMember(member);
        Long novelId = novelService.createNovel(novel);

    }*/




}

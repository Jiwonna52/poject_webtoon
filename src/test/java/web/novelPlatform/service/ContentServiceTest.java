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
import web.novelPlatform.entity.chapters.Chapter;
import web.novelPlatform.entity.chapters.Content;
import web.novelPlatform.repository.ChapterRepository;
import web.novelPlatform.repository.ContentRepository;

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
    ChapterService chapterService;
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    ContentService contentService;
    @Autowired
    ChapterRepository chapterRepository;

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

        Chapter chapter = new Chapter();
        chapter.setNovel(novel);
        chapter.setTitle("1");
        Long chapterId = chapterService.createChapter(chapter);

        //when
        Content content = new Content();
        content.setChapter(chapter);
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
        novel.setGenre(Genre.RomanceFantasy);
        novel.setSerialState(SerialState.Live);
        novel.setMember(member);
        Long novelId = novelService.createNovel(novel);

        //새로운 챕터 등록
        Chapter chapter = new Chapter();
        chapter.setNovel(novel);
        chapter.setTitle("1");
        Long chapterId = chapterService.createChapter(chapter);

        Chapter chapter2 = new Chapter();
        chapter2.setNovel(novel);
        chapter2.setTitle("2");
        Long chapterId2 = chapterService.createChapter(chapter2);

        //새로운 내용 등록
        Content content1 = new Content();
        content1.setChapter(chapter);
        content1.setContents("1화 내용");
        Long contentId1 = contentService.createContent(content1);

        Content content2 = new Content();
        content2.setChapter(chapter);
        content2.setContents("2화 내용");
        Long contentId2 = contentService.createContent(content2);

        Content content3 = new Content();
        content3.setChapter(chapter2);
        content3.setContents("챕터 2의 3화 내용");
        Long contentId3 = contentService.createContent(content3);

        //when
        List<Content> list = contentService.findContentsByChapterId(chapterId2);

        //then
        assertEquals(content3, list.get(0));
        System.out.println(list.get(0).getContents());

    }

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

        Chapter chapter = new Chapter();
        chapter.setNovel(novel);
        chapter.setTitle("1");
        Long chapterId = chapterService.createChapter(chapter);

        Content content1 = new Content();
        content1.setChapter(chapter);
        content1.setContents("1화 내용");
        Long contentId1 = contentService.createContent(content1);

        Content content2 = new Content();
        content2.setChapter(chapter);
        content2.setContents("2화 내용");
        Long contentId2 = contentService.createContent(content2);
        System.out.println("내용의 수 " + chapterRepository.findContentAll(chapterId).size());

        //when
        chapterService.deleteChapter(chapterId);

        //then
        //System.out.println(chapterRepository.findContentAll(chapterId).size());
        assertEquals(content1, contentRepository.findOne(contentId1));

    }


}

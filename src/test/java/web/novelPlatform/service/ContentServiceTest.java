package web.novelPlatform.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.entity.Genre;
import web.novelPlatform.entity.Novel;
import web.novelPlatform.entity.SerialState;
import web.novelPlatform.entity.User;
import web.novelPlatform.entity.chapter.Chapter;
import web.novelPlatform.entity.chapter.Content;
import web.novelPlatform.repository.ChapterRepository;
import web.novelPlatform.repository.ContentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ContentServiceTest {

    @Autowired
    UserService userService;
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
        User user = new User();
        user.setName("A");
        Long userID = userService.join(user);

        Novel novel = new Novel();
        novel.setTitle("악녀한테 너무해!");
        novel.setGenre(Genre.RomanceFantasy);
        novel.setSerialState(SerialState.Live);
        novel.setUser(user);
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
    public void 챕터삭제() throws Exception{
        //Given
        User user = new User();
        user.setName("A");
        Long userID = userService.join(user);

        Novel novel = new Novel();
        novel.setTitle("악녀한테 너무해!");
        novel.setGenre(Genre.RomanceFantasy);
        novel.setSerialState(SerialState.Live);
        novel.setUser(user);
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

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
import web.novelPlatform.entity.User;
import web.novelPlatform.entity.chapter.Chapter;
import web.novelPlatform.repository.ChapterRepository;
import web.novelPlatform.repository.NovelRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ChapterServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    NovelRepository novelRepository;
    @Autowired
    NovelService novelService;

    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    ChapterService chapterService;

    @Test
    public void 챕터등록()throws Exception{
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

        //When
        Chapter chapter = new Chapter();
        chapter.setNovel(novel);
        chapter.setTitle("1");
        Long chapterId = chapterService.createChapter(chapter);

        //Then
        assertEquals(chapter, chapterRepository.findOne(chapterId));
    }
}

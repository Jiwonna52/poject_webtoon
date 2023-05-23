package web.novelPlatform.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.entity.Genre;
import web.novelPlatform.entity.Novel;
import web.novelPlatform.entity.SerialState;
import web.novelPlatform.entity.Member;
import web.novelPlatform.entity.chapters.Chapter;
import web.novelPlatform.repository.ChapterRepository;
import web.novelPlatform.repository.NovelRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ChapterServiceTest {

    @Autowired
    memberService memberService;
    @Autowired
    NovelRepository novelRepository;
    @Autowired
    NovelService novelService;

    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    ChapterService chapterService;

    @Test
    @Rollback(false)
    public void 챕터등록()throws Exception{
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

        //When
        Chapter chapter = new Chapter();
        chapter.setNovel(novel);
        chapter.setTitle("1");
        Long chapterId = chapterService.createChapter(chapter);
        //chapterService.findChapters();
        System.out.println("찾았다!" + chapterService.findNovelChapters(novelId).get(0).getTitle());
        chapterService.findNovelChapters(novelId);

        //Then
        //assertEquals(chapter, chapterRepository.findOne(chapterId));
    }

    /*
    @Test
    public void 회차목록() throws Exception{
        //Given
        User user = new User();
        user.setName("A");
        user.setEmail("AAA");
        Long userId = userService.join(user);

        Novel novel = new Novel();
        novel.setTitle("제목입니다.");
        novel.setUser(user);
        Long novelId = novelService.createNovel(novel);

        Chapter chapter = new Chapter();
        chapter.setNovel(novel);
        chapter.setTitle("첫 번째입니다.");
        Long chapterId = chapterService.createChapter(chapter);

        //When
        List<Chapter> list = chapterService.findNovelChapters(novelId);

        //Then
        System.out.println(list.get(0));
    }*/
}

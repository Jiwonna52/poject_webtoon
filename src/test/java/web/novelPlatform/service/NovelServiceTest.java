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
import web.novelPlatform.repository.NovelRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class NovelServiceTest {

    @Autowired
    NovelRepository novelRepository;
    @Autowired
    NovelService novelService;

    @Autowired
    UserService userService;

    @Test
    public void 소설등록() throws Exception{
        //Given
        User user = new User();
        user.setName("A");
        Long userId = userService.join(user);

        //When
        Novel novel = new Novel();
        novel.setTitle("악녀한테 너무해!");
        novel.setGenre(Genre.RomanceFantasy);
        novel.setSerialState(SerialState.Live);
        novel.setUser(user);
        Long novelId = novelService.createNovel(novel);


        //Then
        assertEquals(novel, novelRepository.findOne(novelId));

    }

    @Test
    public void 소설삭제() throws Exception{
        //Given
        User user = new User();
        user.setName("A");
        Long userId = userService.join(user);

        //When
        Novel novel = new Novel();
        novel.setTitle("악녀한테 너무해!");
        novel.setGenre(Genre.RomanceFantasy);
        novel.setSerialState(SerialState.Live);
        novel.setUser(user);
        Long novelId = novelService.createNovel(novel);
        novelService.deleteNovel(novelId);

        //Then
        assertEquals(novel, novelRepository.findOne(novelId));

    }

    @Test
    public void 소설변경() throws Exception{
        //Given
        User user = new User();
        user.setName("A");
        Long userId = userService.join(user);

        Novel novel = new Novel();
        novel.setTitle("악녀한테 너무해!");
        novel.setGenre(Genre.RomanceFantasy);
        novel.setSerialState(SerialState.Live);
        novel.setUser(user);
        novelService.createNovel(novel);

        //When
        String changeTitle = "변경한 제목";
        novelService.updateNovel(novel.getId(), changeTitle, novel.getIntroduce(), novel.getSerialState(), novel.getGenre());

        //Then
        assertEquals(changeTitle, novel.getTitle());
        //assertEquals("악녀한테 너무해!", novel.getTitle());
    }
}

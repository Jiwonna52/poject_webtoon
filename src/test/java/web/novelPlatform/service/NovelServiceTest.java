package web.novelPlatform.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.Entity.Genre;
import web.novelPlatform.Entity.Novel;
import web.novelPlatform.Entity.SerialState;
import web.novelPlatform.Entity.User;
import web.novelPlatform.Repository.NovelRepository;

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
}

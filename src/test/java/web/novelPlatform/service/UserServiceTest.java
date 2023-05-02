package web.novelPlatform.service;

import org.junit.jupiter.api.Assertions;
import web.novelPlatform.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception{
        User user = new User();
        user.setName("A");
        user.setEmail("AAA@Gmail.com");

        Long saveId = userService.join(user);

        assertEquals(user, userRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        User user1 = new User();
        user1.setName("A");

        User user2 = new User();
        user2.setName("A");

        userService.join(user1);
        Assertions.assertThrows(IllegalStateException.class, () -> userService.join(user2));

        Assertions.fail("예외가 발생해야 합니다.");


    }
}

package web.novelPlatform.service;

import org.junit.jupiter.api.Assertions;
import org.springframework.test.annotation.Rollback;
import web.novelPlatform.entity.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.repository.memberRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    memberService memberService;
    @Autowired
    memberRepository memberRepository;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("A");
        member.setEmail("AAA@Gmail.com");

        Long saveId = memberService.join(member);

        System.out.println("된 거냐????" + memberService.findOne(saveId).getName());

        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        Member member1 = new Member();
        member1.setName("A");

        Member member2 = new Member();
        member2.setName("A");

        memberService.join(member1);
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.fail("예외가 발생해야 합니다.");


    }
}

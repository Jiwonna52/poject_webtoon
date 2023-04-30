package web.novelPlatform;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository = new MemberRepository();

    @Test
    @Transactional
    @Rollback(false)
    public void testMember(){
        Member member = new Member();
        member.setName("memberA");
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

    }
}

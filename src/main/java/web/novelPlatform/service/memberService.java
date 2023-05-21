package web.novelPlatform.service;

import web.novelPlatform.entity.Member;
import web.novelPlatform.repository.memberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //final된 것을 autowired(필드 주입)를 쓰지 않아도 주입 ->
public class memberService {

    private final memberRepository memberRepository;

    @Transactional
    public Long join(Member member)throws IllegalStateException{


        validateDuplicateUser(member);
        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateUser(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 필명입니다.");
        }
    }

    public List<Member> findUsers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


}

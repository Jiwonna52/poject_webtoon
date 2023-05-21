package web.novelPlatform.repository;

import web.novelPlatform.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class memberRepository {


    //EntityManager를 빈으로 주입할 때 사용하는 어노테이션이다.
    @PersistenceContext
    //@Entity 를 달고 있는 엔티티 객체들을 관리해준다.
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
    }


}

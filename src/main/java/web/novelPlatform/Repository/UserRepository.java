package web.novelPlatform.Repository;

import org.aspectj.weaver.loadtime.definition.LightXMLParser;
import web.novelPlatform.Entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {


    //EntityManager를 빈으로 주입할 때 사용하는 어노테이션이다.
    @PersistenceContext
    //@Entity 를 달고 있는 엔티티 객체들을 관리해준다.
    private EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public List<User> findByName(String name){
        return em.createQuery("select u from User u where u.name = :name", User.class).setParameter("name", name).getResultList();
    }


}

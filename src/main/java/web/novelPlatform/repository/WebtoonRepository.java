package web.novelPlatform.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import web.novelPlatform.entity.Genre;
import web.novelPlatform.entity.Novel;
import web.novelPlatform.entity.SerialState;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NovelRepository {

    private final EntityManager em;

    //소설 등록
    public Long create(Novel novel){
        em.persist(novel);
        return novel.getId();
    }

    //소설 삭제
    public void delete(Long id){
        em.remove(findOne(id));
    }

    //id로 소설 찾기
    public Novel findOne(Long id){
        return em.find(Novel.class, id);
    }

    //소설 전체 조회
    public List<Novel> findAll(){
        return em.createQuery("select n from Novel n", Novel.class).getResultList();
    }

    /*
    완결 소설 찾기
    public List<Novel> findFinish(){
        return em.createQuery("")
    }*/
}

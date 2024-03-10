package web.novelPlatform.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import web.novelPlatform.entity.Webtoon;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebtoonRepository {

    private final EntityManager em;



    //id로 소설 찾기
    public Webtoon findOne(Long id){
        return em.find(Webtoon.class, id);
    }

    //소설 전체 조회
    public List<Webtoon> findAll(){
        return em.createQuery("select n from Webtoon n", Webtoon.class).getResultList();
    }


}

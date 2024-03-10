package web.novelPlatform.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import web.novelPlatform.entity.Content;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContentRepository{

    private final EntityManager em;

    //하나 찾기
    public List<Content> findOne(Long webtoonId, Long contentId){
        //타입 정보르 받을 수 있으니까 타입 쿼리를 슨다.
        TypedQuery<Content> query = em.createQuery("select c from Content c where c.webtoon.id = :webtoonId AND c.id = :contentId", Content.class)
                .setParameter("webtoonId", webtoonId).setParameter("contentId", contentId);

        return em.createQuery("select c from Content c where c.webtoon.id = :webtoonId AND c.id = :contentId")
                .setParameter("webtoonId", webtoonId).setParameter("contentId", contentId).getResultList();
    }

    //전부 찾기
    public List<Content> findAll(){

        return em.createQuery("select c from Content c", Content.class).getResultList();
    }

    public List<Content> findContentByNovelId(Long webtoonId){
        return em.createQuery("select c from Content c where c.webtoon.id = :id").setParameter("id", webtoonId).getResultList();
    }



}

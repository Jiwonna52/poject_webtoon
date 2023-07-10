package web.novelPlatform.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import web.novelPlatform.entity.Content;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContentRepository{

    private final EntityManager em;

    //등록
    public Long create(Content content){
        em.persist(content);

        return content.getId();
    }

    //삭제 -> 여기서는 굳이 하위 항목을 찾을 필요가 없다(사실 찾을 하위 항목도 없다)

    public void delete(Long novelId, Long contentId){
        em.remove(findOne(novelId, contentId));
    }

    //하나 찾기
    public List<Content> findOne(Long novelId, Long contentId){
        return em.createQuery("select c from Content c where c.novel.id = :novelId AND c.id = :contentId")
                .setParameter("novelId", novelId).setParameter("contentId", contentId).getResultList();
        //return em.find(Content.class, id);
    }

    //전부 찾기
    public List<Content> findAll(){

        return em.createQuery("select c from Content c", Content.class).getResultList();
    }

    public List<Content> findContentByNovelId(Long novelId){
        return em.createQuery("select c from Content c where c.novel.id = :id").setParameter("id", novelId).getResultList();
    }


}

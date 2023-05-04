package web.novelPlatform.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import web.novelPlatform.entity.chapter.Content;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContentRepository {

    private final EntityManager em;

    //등록
    public Long create(Content content){
        em.persist(content);

        return content.getId();
    }

    //삭제 -> 여기서는 굳이 하위 항목을 찾을 필요가 없다(사실 찾을 하위 항목도 없다)
    public void delete(Long id){
        em.remove(findOne(id));
    }

    //하나 찾기
    public Content findOne(Long id){
        return em.find(Content.class, id);
    }

    //전부 찾기
    public List<Content> findAll(){

        return em.createQuery("select sc from SmallChapter", Content.class).getResultList();
    }

}

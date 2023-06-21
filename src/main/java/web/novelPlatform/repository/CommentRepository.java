package web.novelPlatform.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import web.novelPlatform.entity.Comment;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    //등록
    public Long create(Comment comment){
        em.persist(comment);
        return comment.getId();
    }

    //삭제
    public void delete(Long id){
        em.remove(findOne(id));
    }

    //하나 찾기
    public Comment findOne(Long id){
        return em.find(Comment.class, id);
    }

    //여러 개 찾기
    public List<Comment> findAll(){
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }
}

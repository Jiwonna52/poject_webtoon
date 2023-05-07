package web.novelPlatform.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import web.novelPlatform.entity.chapter.Chapter;
import web.novelPlatform.entity.chapter.Content;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChapterRepository {

    private final EntityManager em;

    //BigChapter 등록
    //이름이 중복되도 된다.
    public Long create(Chapter chapter){
        em.persist(chapter);
        return chapter.getId();
    }

    //BigChapter 삭제 -> 밑에 smallChapter가 하나라도 있으면 삭제될 수 없음
    //위의 기능은 BigChapterService에 넣어준다.
    public void delete(Long id){
        em.remove(findOne(id));
    }

    //BigChapter를 하나 찾아온다.
    public Chapter findOne(Long id) {
        return em.find(Chapter.class, id);
    }

    //BigChapter 전체를 조회한다.
    public List<Chapter> findAll(){
        return em.createQuery("select c form Chapter c", Chapter.class).getResultList();
    }

    //여기서 쿼리 날릴 때 BigChapter하고 연관된 SmallChapter만 가지고 와야 한다.
    //전부 가지고 오면 다른 소설의 것도 가져옴!
    public List<Content> findContentAll(Long id){
        //일단 이렇게 하고 나중에 적당한 쿼리를 찾자.
        Chapter chapter = findOne(id);
        return chapter.getContents();
        //return em.createQuery("select sc from SmallChapter where sc.bigChapter = : id", SmallChapter.class).getResultList();
    }


}

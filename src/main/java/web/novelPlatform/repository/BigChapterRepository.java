package web.novelPlatform.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import web.novelPlatform.entity.chapter.BigChapter;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BigChapterRepository {

    private final EntityManager em;

    //BigChapter 등록
    public Long create(BigChapter bigChapter){
        em.persist(bigChapter);
        return bigChapter.getId();
    }

    //BigChapter 삭제 -> 밑에 smallChapter가 하나라도 있으면 삭제될 수 없음


    //id로 BigChapter 찾기



}

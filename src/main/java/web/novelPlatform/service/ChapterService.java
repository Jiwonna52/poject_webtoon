package web.novelPlatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.entity.chapter.Chapter;
import web.novelPlatform.entity.chapter.Content;
import web.novelPlatform.repository.ChapterRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChapterService {

    //위에 RequiredArgsConstructor를 넣어줬기 때문에 Atribute를 따로 설정할 필요는 없다.
    //final이 붙거나 하나 있으면 자동으로 영속성 관리함
    private final ChapterRepository ChapterRepository;

    @Transactional
    //같은 이름 중복해도 된다.
    public Long createChapter(Chapter chapter){
        ChapterRepository.create(chapter);
        return chapter.getId();
    }

    @Transactional
    //삭제 기능 -> 만일 하위 내용이 있다면 삭제하면 안 된다.
    public void deleteChapter(Long id){
        /*
        try{
            findContent(id);
        }catch (IllegalStateException e){
            System.out.println("소설을 삭제할 수 없습니다.");
            throw new IllegalStateException();
        }

        //findContent(id);*/
        ChapterRepository.delete(id);

    }

    /*
    이건 나중에 구현
    public void findContent(Long id){
        //id에 해당하는 BigChapter 하위의 SmallChapter를 찾아가지고 만일 리스트에 값이 있으면 예외 호출
        List<Content> findContents = ChapterRepository.findContentAll(id);

        if(!findContents.isEmpty()){
            throw new IllegalStateException("하위 항목이 존재해 삭제할 수 없습니다.");
        }
    }*/

    //하나 찾아오는 것
    public Chapter findOne(Long id){
        return ChapterRepository.findOne(id);
    }

    //여러 개 찾아오는 것
    public List<Chapter> findChapters(){
        return ChapterRepository.findAll();
    }
}

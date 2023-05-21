package web.novelPlatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.entity.chapters.Content;
import web.novelPlatform.repository.ContentRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    @Transactional
    //내용 추가
    public Long createContent(Content content){
        contentRepository.create(content);

        return content.getId();
    }

    @Transactional
    public void deleteContent(Long id){
        contentRepository.delete(id);
    }

    //내용 바꾸기
    @Transactional
    public void updateContent(Long id, String contents){
        Content content = findOne(id);

        content.setId(id);
        content.setContents(contents);
    }

    //하나 찾기
    private Content findOne(Long id) {
        return contentRepository.findOne(id);
    }


    //전부 찾기 -> 굳이?
    private List<Content> findAllContents(){
        return contentRepository.findAll();
    }

}

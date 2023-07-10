package web.novelPlatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.entity.Content;
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
    public void deleteContent(Long novelId, Long contentId){
        contentRepository.delete(novelId, contentId);
    }

    //내용 바꾸기
    @Transactional
    public void updateContent(Long novelId, Long contentId, String contents, String title){
        List<Content> content = findOne(novelId, contentId);

        //연결된 소설하고 댓글들 목록은 바꾸면 안 된다.
        content.get(0).setContents(contents);
        content.get(0).setTitle(title);
    }

    //특정한 소설에서 한 회차 찾기
    public List<Content> findOne(Long novelId, Long contentId) {
        return contentRepository.findOne(novelId, contentId);
    }


    //전부 찾기 -> 굳이?
    private List<Content> findAllContents(){
        return contentRepository.findAll();
    }

    public List<Content> findContentByNovelId(Long novelId){
        return contentRepository.findContentByNovelId(novelId);
    }
}

package web.novelPlatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.entity.Genre;
import web.novelPlatform.entity.Novel;
import web.novelPlatform.entity.SerialState;
import web.novelPlatform.repository.NovelRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelService {

    private final NovelRepository novelRepository;

    @Transactional
    public Long createNovel(Novel novel){
        return novelRepository.create(novel);
    }

    public void deleteNovel(Long id){
        novelRepository.delete(id);
    }

    //update
    @Transactional
    public void updateNovel(Long id, String title, String introduce, SerialState serialState, Genre genre){
        Novel novel = novelRepository.findOne(id);

        //작가(user)는 바꾸면 안 된다!
        novel.setTitle(title);
        novel.setIntroduce(introduce);
        novel.setSerialState(serialState);
        novel.setGenre(genre);

    }

    public List<Novel> findNovels(){
        return novelRepository.findAll();
    }

    public Novel findOne(Long id){
        return novelRepository.findOne(id);
    }

}

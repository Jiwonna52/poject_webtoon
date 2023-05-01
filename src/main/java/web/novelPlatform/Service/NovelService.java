package web.novelPlatform.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.Entity.Novel;
import web.novelPlatform.Repository.NovelRepository;

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

    public List<Novel> findNovels(){
        return novelRepository.findAll();
    }

    public Novel findOne(Long id){
        return novelRepository.findOne(id);
    }

}

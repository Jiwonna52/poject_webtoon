package web.novelPlatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.novelPlatform.entity.Webtoon;
import web.novelPlatform.repository.WebtoonRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;


    public List<Webtoon> findNovels(){
        return webtoonRepository.findAll();
    }

    public Webtoon findOne(Long id){
        return webtoonRepository.findOne(id);
    }

}

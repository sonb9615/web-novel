package numble.webnovel.novel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.dto.NovelRegisterRequest;
import numble.webnovel.novel.repository.NovelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NovelService {

    private final NovelRepository novelRepository;

    @Transactional
    public void registerNovel(NovelRegisterRequest request){
        Novel novel = request.toNovel();
        novelRepository.save(novel);
    }
}

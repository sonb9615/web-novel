package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.repository.NovelRepository;
import numble.webnovel.repository.NovelTagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelTagService {

    private final NovelTagRepository novelTagInterface;
    private final NovelRepository novelRepository;


}

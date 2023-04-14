package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.repository.NovelEpisodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelEpisodeService {

    private final NovelEpisodeRepository novelEpisodeRepository;
}

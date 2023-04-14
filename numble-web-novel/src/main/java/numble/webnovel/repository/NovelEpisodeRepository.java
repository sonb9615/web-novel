package numble.webnovel.repository;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.NovelEpisode;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class NovelEpisodeRepository {

    private final EntityManager em;

    //에피소드 저장
    public void save(NovelEpisode novelEpisode){em.persist(novelEpisode);}




}

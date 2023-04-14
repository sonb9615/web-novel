package numble.webnovel.repository;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.NovelEpisode;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NovelEpisodeRepository {

    private final EntityManager em;

    //에피소드 저장
    public void save(NovelEpisode novelEpisode){em.persist(novelEpisode);}

    public NovelEpisode findById(String episodeId){
        return em.find(NovelEpisode.class, episodeId);
    }

    public List<NovelEpisode> findByNovelId(String novelId){
        return em.createQuery("select e from NovelEpisode e where e.novelId = :novelId order by e.regDt")
                .setParameter("novelId", novelId)
                .getResultList();
    }
}

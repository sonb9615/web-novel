package numble.webnovel.repository;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.CacheChargeHis;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CacheChargeHisRepository {

    private final EntityManager em;

    public void save(CacheChargeHis cacheChargeHis){em.persist(cacheChargeHis);}


}

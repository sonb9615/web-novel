package numble.webnovel.repository;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.ChildCode;
import numble.webnovel.domain.ParentCode;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CommonCodeRepository {

    private final EntityManager em;

    // 부모코드 저장
    public void saveParentCode(ParentCode parentCode){em.persist(parentCode);}

    // 부모코드로 select
    public ParentCode findByCode(String parentCode){
        return em.find(ParentCode.class, parentCode);
    }

    // 자식코드 저장
    public void saveChildCode(ChildCode childCode){em.persist(childCode);}

    // 자식코드로 select
    public ChildCode findByChildCode(String childCode){
        return em.find(ChildCode.class, childCode);
    }
}

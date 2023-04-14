package numble.webnovel.repository;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.Novel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class NovelRepository {

    private final EntityManager em;

    // 소설 저장
    public void save(Novel novel){em.persist(novel);}

    //소설 삭제
    public void delete(String novelId){

    }

    //소설 수정
    public void update(Novel novel){

    }
}

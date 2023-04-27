package numble.webnovel.repository;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.Novel;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class NovelRepository{

    private final EntityManager em;

    // 소설 저장
    public void save(Novel novel){em.persist(novel);}

    public Novel findById(String novelId){
        return em.find(Novel.class, novelId);
    }

    //소설 삭제
    public void delete(String novelId){
        Novel novel = this.findById(novelId);
        if(novel == null) throw new CommonException(ExceptionEnum.RESULT_NOT_EXIST_EXCEPTION);
        em.remove(novel);
    }

    //소설 수정
    public void update(Novel novel){

    }
}

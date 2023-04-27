package numble.webnovel.repository;

import numble.webnovel.domain.NovelTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NovelTagRepository extends JpaRepository<NovelTag, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from NovelTag t where t.id = :tagId")
    void deleteAllByTagId(List<Long> tagId);

}

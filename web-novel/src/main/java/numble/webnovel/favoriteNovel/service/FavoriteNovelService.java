package numble.webnovel.favoriteNovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.exception.WebNovelServiceException;
import numble.webnovel.favoriteNovel.domain.FavoriteNovel;
import numble.webnovel.favoriteNovel.dto.FavoriteNovelInfoResponseList;
import numble.webnovel.favoriteNovel.repository.FavoriteNovelRepository;
import numble.webnovel.member.domain.Member;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.repository.NovelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static numble.webnovel.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class FavoriteNovelService {

    private final FavoriteNovelRepository favoriteNovelRepository;
    private final NovelRepository novelRepository;

    public void registerFavoriteNovel(Member member, Long novelId){
        Novel novel = novelRepository.findById(novelId)
                .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_NOVEL));
        if(favoriteNovelRepository.existsByNovelAndMember(novel, member)){
            throw new WebNovelServiceException(ALREADY_FAVORITE_NOVEL);
        }
        FavoriteNovel favoriteNovel = FavoriteNovel.builder()
                                        .novel(novel)
                                        .member(member)
                                        .build();
        favoriteNovelRepository.save(favoriteNovel);
    }

    public void deleteFavoriteNovel(Member member, Long novelId){
        Novel novel = novelRepository.findById(novelId)
                .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_NOVEL));
        FavoriteNovel favoriteNovel = findByNovelAndMember(novel, member);
        favoriteNovelRepository.delete(favoriteNovel);
    }

    @Transactional(readOnly = true)
    public FavoriteNovelInfoResponseList showFavoriteNovelsByMember(Member member){
        List<FavoriteNovel> favoriteNovels = findFavoriteNovelByMember(member.getMemberId());
        return FavoriteNovelInfoResponseList.toFavoriteNovelInfoResponseList(favoriteNovels);
    }

    private List<FavoriteNovel> findFavoriteNovelByMember(Long memberId){
        return favoriteNovelRepository.findByMemberWithNovel(memberId)
                .orElseThrow(() -> new WebNovelServiceException(EMPTY_FAVORITE_NOVEL));
    }

    private FavoriteNovel findByNovelAndMember(Novel novel, Member member){
        return favoriteNovelRepository.findByNovelAndMember(novel, member)
                .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_FAVORITE_NOVEL));
    }
}

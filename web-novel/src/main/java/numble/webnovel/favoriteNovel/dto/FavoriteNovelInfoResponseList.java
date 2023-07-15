package numble.webnovel.favoriteNovel.dto;

import lombok.Getter;
import numble.webnovel.favoriteNovel.domain.FavoriteNovel;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FavoriteNovelInfoResponseList {

    private List<FavoriteNovelInfoResponse> favoriteNovelInfoResponseList;

    public FavoriteNovelInfoResponseList(List<FavoriteNovelInfoResponse> favoriteNovelInfoResponseList) {
        this.favoriteNovelInfoResponseList = favoriteNovelInfoResponseList;
    }

    public static FavoriteNovelInfoResponseList toFavoriteNovelInfoResponseList(List<FavoriteNovel> favoriteNovels){

        List<FavoriteNovelInfoResponse> responseList = favoriteNovels.stream()
                .map(FavoriteNovelInfoResponse::toFavoriteNovelInfoResponse)
                .collect(Collectors.toList());

        return new FavoriteNovelInfoResponseList(responseList);
    }
}

package numble.webnovel.favoriteNovel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.favoriteNovel.dto.FavoriteNovelInfoResponseList;
import numble.webnovel.favoriteNovel.service.FavoriteNovelService;
import numble.webnovel.util.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/favorite-novel")
public class FavoriteNovelController {

    private final FavoriteNovelService favoriteNovelService;

    @PostMapping("/{novelId}")
    public ResponseEntity<CommonResponse<Void>> registerFavoriteNovel(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long novelId){
        favoriteNovelService.registerFavoriteNovel(userDetails.getMember(), novelId);
        return new ResponseEntity<>(new CommonResponse<>("선호작 등록 완료", null), HttpStatus.CREATED);
    }

    @DeleteMapping("/{novelId}")
    public ResponseEntity<CommonResponse<Void>> deleteFavoriteNovel(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long novelId){
        favoriteNovelService.deleteFavoriteNovel(userDetails.getMember(), novelId);
        return new ResponseEntity<>(new CommonResponse<>("선호작 삭제 완료", null), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<FavoriteNovelInfoResponseList>> showFavoriteNovels(@AuthenticationPrincipal UserDetailsImpl userDetails){
        FavoriteNovelInfoResponseList responseList = favoriteNovelService.showFavoriteNovelsByMember(userDetails.getMember());
        return new ResponseEntity<>(new CommonResponse<>("선호작 리스트 조회 완료", responseList), HttpStatus.OK);
    }
}

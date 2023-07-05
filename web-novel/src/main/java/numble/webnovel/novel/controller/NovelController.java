package numble.webnovel.novel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.novel.dto.NovelDetailInfoResponse;
import numble.webnovel.novel.dto.NovelInfoResponseList;
import numble.webnovel.novel.dto.NovelRegisterRequest;
import numble.webnovel.novel.dto.NovelUpdateRequest;
import numble.webnovel.novel.service.NovelService;
import numble.webnovel.util.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/novel")
public class NovelController {

    private final NovelService novelService;

    @PostMapping
    public ResponseEntity<CommonResponse<Void>> registerNovel(@RequestBody NovelRegisterRequest request){
        novelService.registerNovel(request);
        return new ResponseEntity<>(new CommonResponse<>("소설 등록 성공", null), HttpStatus.CREATED);
    }

    @PutMapping("/{novelId}")
    public ResponseEntity<CommonResponse<Void>> updateNovel(@PathVariable Long novelId, @RequestBody NovelUpdateRequest request){
        novelService.updateNovel(novelId, request);
        return new ResponseEntity<>(new CommonResponse<>("소설 정보 업데이트 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/{novelId}")
    public ResponseEntity<CommonResponse<Void>> deleteNovel(@PathVariable Long novelId){
        novelService.deleteNovel(novelId);
        return new ResponseEntity<>(new CommonResponse<>("소설 삭제 성공", null), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponse<NovelInfoResponseList>> showNovelList(){
        NovelInfoResponseList responseList = novelService.showNovelList();
        return new ResponseEntity<>(new CommonResponse<>("소설 리스트 조회 성공", responseList), HttpStatus.OK);
    }

    @GetMapping("/search/{searchWord}")
    public ResponseEntity<CommonResponse<NovelInfoResponseList>> showNovelListBySearchWord(@PathVariable String searchWord){
        NovelInfoResponseList responseList = novelService.showNovelListBySearchWord(searchWord);
        return new ResponseEntity<>(new CommonResponse<>("소설 리스트 조회 성공", responseList), HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<CommonResponse<NovelInfoResponseList>> showNovelListByGenre(@PathVariable String genre){
        NovelInfoResponseList responseList = novelService.showNovelListByGenre(genre);
        return new ResponseEntity<>(new CommonResponse<>("소설 리스트 조회 성공", responseList), HttpStatus.OK);
    }

    @GetMapping("/status/{serialStatus}")
    public ResponseEntity<CommonResponse<NovelInfoResponseList>> showNovelListBySerialStatus(@PathVariable String serialStatus){
        NovelInfoResponseList responseList = novelService.showNovelListBySerialStatus(serialStatus);
        return new ResponseEntity<>(new CommonResponse<>("소설 리스트 조회 성공", responseList), HttpStatus.OK);
    }

    @GetMapping("/{novelId}/details")
    public ResponseEntity<CommonResponse<NovelDetailInfoResponse>> showNovelDetails(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long novelId){
        NovelDetailInfoResponse response = novelService.showNovelDetails(userDetails.getMember(), novelId);
        return new ResponseEntity<>(new CommonResponse<>("소설 상세 정보 조회 성공", response), HttpStatus.OK);
    }
}

package numble.webnovel.library.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.library.dto.OwnEpisodeReadInfoResponse;
import numble.webnovel.library.dto.OwnEpisodeReadRequest;
import numble.webnovel.library.service.LibraryService;
import numble.webnovel.util.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/library")
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping("/{episodeId}")
    public ResponseEntity<CommonResponse<Void>> purchaseEpisode(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long episodeId){
        libraryService.purchaseEpisode(userDetails.getMember(), episodeId);
        return new ResponseEntity<>(new CommonResponse<>("에피소드 구매 완료", null), HttpStatus.OK);
    }

    @PutMapping("/read/{episodeId}")
    public ResponseEntity<CommonResponse<OwnEpisodeReadInfoResponse>> readOwnEpisode(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long episodeId){
        OwnEpisodeReadInfoResponse response = libraryService.readOwnEpisode(userDetails.getMember(), episodeId);
        return new ResponseEntity<>(new CommonResponse<>("에피소드 열람 성공", response), HttpStatus.OK);
    }

    @PutMapping("/read/{episodeId}/next-page")
    public ResponseEntity<CommonResponse<Void>> readNextPageOwnEpisode(@AuthenticationPrincipal UserDetailsImpl userDetails
            , @PathVariable Long episodeId, @RequestBody OwnEpisodeReadRequest request){
        libraryService.readNextPage(userDetails.getMember(), episodeId, request);
        return new ResponseEntity<>(new CommonResponse<>("에피소드 다음 페이지 열람 성공", null), HttpStatus.OK);
    }

    @PutMapping("/read/{episodeId}/previous-page")
    public ResponseEntity<CommonResponse<Void>> readPreviousPageOwnEpisode(@AuthenticationPrincipal UserDetailsImpl userDetails
            , @PathVariable Long episodeId, @RequestBody OwnEpisodeReadRequest request){
        libraryService.readPreviousPage(userDetails.getMember(), episodeId, request);
        return new ResponseEntity<>(new CommonResponse<>("에피소드 이전 페이지 열람 성공", null), HttpStatus.OK);
    }
}

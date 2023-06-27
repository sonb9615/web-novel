package numble.webnovel.library.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.library.service.LibraryService;
import numble.webnovel.util.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

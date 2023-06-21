package numble.webnovel.episode.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.episode.dto.EpisodeRegisterRequest;
import numble.webnovel.episode.dto.EpisodeUpdateRequest;
import numble.webnovel.episode.service.EpisodeService;
import numble.webnovel.exception.WebNovelServiceException;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.repository.NovelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static numble.webnovel.exception.ErrorCode.NO_EXISTS_NOVEL;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/episode")
public class EpisodeController {

    private final EpisodeService episodeService;
    private final NovelRepository novelRepository;

    @PostMapping
    public ResponseEntity<CommonResponse<Void>> registerEpisode(@RequestParam Long novelId, @RequestBody EpisodeRegisterRequest request){
        Novel novel = novelRepository.findById(novelId)
                .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_NOVEL));
        episodeService.registerEpisode(novel, request);
        return new ResponseEntity<>(new CommonResponse<>("에피소드 등록 성공", null), HttpStatus.CREATED);
    }

    @PutMapping("/{episodeId}")
    public ResponseEntity<CommonResponse<Void>> updateEpisode(@PathVariable Long episodeId, @RequestBody EpisodeUpdateRequest request){
        episodeService.updateEpisode(episodeId, request);
        return new ResponseEntity<>(new CommonResponse<>("에피소드 업데이트 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/{episodeId}")
    public ResponseEntity<CommonResponse<Void>> deleteEpisode(@PathVariable Long episodeId){
        episodeService.deleteEpisode(episodeId);
        return new ResponseEntity<>(new CommonResponse<>("에피소드 삭제 완료", null), HttpStatus.OK);
    }
}

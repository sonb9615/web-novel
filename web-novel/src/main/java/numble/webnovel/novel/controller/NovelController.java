package numble.webnovel.novel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.novel.dto.NovelRegisterRequest;
import numble.webnovel.novel.dto.NovelUpdateRequest;
import numble.webnovel.novel.service.NovelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}

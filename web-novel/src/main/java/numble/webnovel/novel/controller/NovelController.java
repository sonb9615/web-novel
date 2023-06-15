package numble.webnovel.novel.controller;

import lombok.RequiredArgsConstructor;
import numble.webnovel.common.CommonResponse;
import numble.webnovel.novel.dto.NovelRegisterRequest;
import numble.webnovel.novel.service.NovelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/novel")
public class NovelController {

    private final NovelService novelService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse<Void>> registerNovel(@RequestBody NovelRegisterRequest request){
        novelService.registerNovel(request);
        return new ResponseEntity<>(new CommonResponse<>("소설 등록 성공", null), HttpStatus.CREATED);
    }

}

package numble.webnovel.novel.dto;

import lombok.Getter;
import numble.webnovel.novel.domain.Novel;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class NovelInfoResponseList {

    private List<NovelInfoResponse> novelList;

    public NovelInfoResponseList(List<NovelInfoResponse> novelList) {
        this.novelList = novelList;
    }

    public static NovelInfoResponseList toNovelInfoResponseList(List<Novel> novels){

        List<NovelInfoResponse> responseList = novels.stream()
                .map(NovelInfoResponse :: toNovelInfoResponse)
                .collect(Collectors.toList());

        return new NovelInfoResponseList(responseList);
    }
}

package numble.webnovel.library.dto;

import lombok.Builder;
import lombok.Getter;
import numble.webnovel.library.domain.Library;

@Getter
public class OwnEpisodeReadInfoResponse {

    private String content;
    private int currentPage;

    @Builder
    public OwnEpisodeReadInfoResponse(String content, int currentPage) {
        this.content = content;
        this.currentPage = currentPage;
    }

    public static OwnEpisodeReadInfoResponse toInfoResponse(Library library){
        return OwnEpisodeReadInfoResponse.builder()
                .content(library.getEpisode().getContent())
                .currentPage(library.getLastReadPage())
                .build();
    }

}

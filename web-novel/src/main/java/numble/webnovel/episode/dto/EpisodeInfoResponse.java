package numble.webnovel.episode.dto;

import lombok.Builder;
import lombok.Getter;
import numble.webnovel.episode.domain.Episode;
import numble.webnovel.library.domain.Library;

import java.time.LocalDateTime;

@Getter
public class EpisodeInfoResponse {

    private Long episodeId;
    private int episodeNo;
    private String episodeTitle;
    private int totalPage;
    private boolean freeYn;
    private int capacity;
    private LocalDateTime regDt;
    private boolean isOwnedEpisode;
    private LocalDateTime lastReadDate;

    @Builder
    public EpisodeInfoResponse(Long episodeId, int episodeNo, String episodeTitle, int totalPage, boolean freeYn, int capacity, LocalDateTime regDt, boolean isOwnedEpisode, LocalDateTime lastReadDate) {
        this.episodeId = episodeId;
        this.episodeNo = episodeNo;
        this.episodeTitle = episodeTitle;
        this.totalPage = totalPage;
        this.freeYn = freeYn;
        this.capacity = capacity;
        this.regDt = regDt;
        this.isOwnedEpisode = isOwnedEpisode;
        this.lastReadDate = lastReadDate;
    }

    public static EpisodeInfoResponse toEpisodeInfoResponse(Episode episode, Library library){
        boolean isOwnEpisode = library != null;
        LocalDateTime lastReadDate = isOwnEpisode ? library.getLastReadDate() : null;

        return EpisodeInfoResponse.builder()
                .episodeId(episode.getEpisodeId())
                .episodeNo(episode.getEpisodeNo())
                .episodeTitle(episode.getEpisodeTitle())
                .totalPage(episode.getTotalPage())
                .freeYn(episode.isFreeYn())
                .regDt(episode.getUdtDt())
                .isOwnedEpisode(isOwnEpisode)
                .lastReadDate(lastReadDate)
                .build();
    }

}

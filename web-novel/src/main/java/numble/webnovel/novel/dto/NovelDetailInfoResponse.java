package numble.webnovel.novel.dto;

import lombok.Builder;
import lombok.Getter;
import numble.webnovel.episode.domain.Episode;
import numble.webnovel.episode.dto.EpisodeInfoResponse;
import numble.webnovel.library.domain.Library;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.enums.Genre;
import numble.webnovel.novel.enums.SerialStatus;

import java.util.List;
import java.util.Objects;

@Getter
public class NovelDetailInfoResponse {

    private Long novelId;
    private String title;
    private String author;
    private String novelImg;
    private String serialStatus;
    private String genre;
    private List<EpisodeInfoResponse> episodes;

    @Builder
    public NovelDetailInfoResponse(Long novelId, String title, String author, String novelImg, String serialStatus, String genre, List<EpisodeInfoResponse> episodes) {
        this.novelId = novelId;
        this.title = title;
        this.author = author;
        this.novelImg = novelImg;
        this.serialStatus = serialStatus;
        this.genre = genre;
        this.episodes = episodes;
    }

    public static NovelDetailInfoResponse toResponse(Novel novel, List<Episode> episodes, List<Library> libraryList){
        String statusName = SerialStatus.toStatusName(novel.getSerialStatus());
        String genreName = Genre.toGenreName(novel.getGenre());

        List<EpisodeInfoResponse> responseList = episodes.stream()
                .map(episode -> EpisodeInfoResponse.toEpisodeInfoResponse(
                        episode
                        , libraryList.stream()
                                .filter(Objects::nonNull)
                                .filter(library -> library.getEpisode().getEpisodeId().equals(episode.getEpisodeId()))
                                .findFirst()
                                .orElse(null)))
                .toList();

        return NovelDetailInfoResponse.builder()
                .novelId(novel.getNovelId())
                .title(novel.getTitle())
                .author(novel.getAuthor())
                .novelImg(novel.getNovelImg())
                .serialStatus(statusName)
                .genre(genreName)
                .episodes(responseList)
                .build();
    }
}

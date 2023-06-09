package numble.webnovel.episode.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.webnovel.library.domain.Library;
import numble.webnovel.novel.domain.Novel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "episode")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Episode {

    @Id
    @Column(name = "episode_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long episodeId;

    @Column(nullable = false)
    private int episodeNo;
    @Column(nullable = false)
    private String episodeTitle;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int totalPage;
    @Column(columnDefinition = "false")
    private boolean freeYn;
    @Column(nullable = false)
    private int capacity;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id")
    private Novel novel;

    @OneToMany(mappedBy = "episode")
    private List<Library> libraryList = new ArrayList<>();

    @Builder
    public Episode(Long episodeId, int episodeNo, String episodeTitle, String content, int totalPage, boolean freeYn, int capacity, LocalDateTime regDt, LocalDateTime udtDt, Novel novel) {
        this.episodeId = episodeId;
        this.episodeNo = episodeNo;
        this.episodeTitle = episodeTitle;
        this.content = content;
        this.totalPage = totalPage;
        this.freeYn = freeYn;
        this.capacity = capacity;
        this.regDt = regDt;
        this.udtDt = udtDt;
        this.novel = novel;
    }

    public void addLibraryList(Library library){
        this.libraryList.add(library);
    }

    public void updateContent(String content){
        this.content = content;
    }

    public void updateFreeYn(boolean freeYn){
        this.freeYn = freeYn;
    }

    public void updateTotalPage(int totalPage){
        this.totalPage = totalPage;
    }

    public void updateCapacity(int capacity){
        this.capacity = capacity;
    }

    public void updateUdtDt(){
        this.udtDt = LocalDateTime.now();
    }

    public boolean checkNextPage(int currentPage){
        return currentPage + 1 <= this.totalPage;
    }

    public boolean checkPreviousPage(int currentPage){
        return currentPage - 1 >= 1;
    }
}

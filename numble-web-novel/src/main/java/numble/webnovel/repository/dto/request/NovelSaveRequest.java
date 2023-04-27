package numble.webnovel.repository.dto.request;

import lombok.Data;
import numble.webnovel.domain.Novel;
import numble.webnovel.enums.CommonStatusEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class NovelSaveRequest {

    private String novelId;
    private String title;
    private String author;
    private String novelInfo;
    private String novelImg;
    private int episodeCost;
    private String genre;
    @Enumerated(EnumType.STRING)
    private CommonStatusEnum status;

    public Novel toNovel(){
        return Novel.createNovel(this.novelId, this.title, this.author
                ,this.novelInfo, this.novelImg, this.episodeCost, this.genre);
    }

}

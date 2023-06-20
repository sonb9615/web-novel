package numble.webnovel.novel.dto;

import lombok.Getter;

@Getter
public class NovelUpdateRequest {

    private String serialStatus;
    private String novelInfo;
    private String novelImg;

    // For Test
    public void setSerialStatusForTest(String status){
        this.serialStatus = status;
    }

    public void setNovelInfoForTest(String novelInfo){
        this.novelInfo = novelInfo;
    }

    public void setNovelImgForTest(String novelImg){
        this.novelImg = novelImg;
    }
}

package numble.webnovel.novel.enums;

import numble.webnovel.exception.WebNovelServiceException;

import static numble.webnovel.exception.ErrorCode.NO_VALID_NOVEL_STATUS;

public enum NovelStatus {

    SERIES("연재중")
    ,RECESS("휴재중")
    ,COMPLETE("완결")
    ,TERMINATION("종료")
    ,SCHEDULE("연재예정")
    ;

    private final String status;

    NovelStatus(String status) { this.status = status; }

    public String getStatus() { return status; }

    public static NovelStatus getNovelStatus(String status){
        return switch (status){
            case "연재중" -> SERIES;
            case "휴재중" -> RECESS;
            case "완결" -> COMPLETE;
            case "종료" -> TERMINATION;
            case "연재예정" -> SCHEDULE;
            default -> throw new WebNovelServiceException(NO_VALID_NOVEL_STATUS);
        };
    }
}

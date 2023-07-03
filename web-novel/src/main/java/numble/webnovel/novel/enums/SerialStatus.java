package numble.webnovel.novel.enums;

import numble.webnovel.exception.WebNovelServiceException;

import static numble.webnovel.exception.ErrorCode.NO_VALID_NOVEL_STATUS;

public enum SerialStatus {

    SERIES("연재중")
    ,RECESS("휴재중")
    ,COMPLETE("완결")
    ,TERMINATION("종료")
    ,SCHEDULE("연재예정")
    ;

    private final String statusName;

    SerialStatus(String status) { this.statusName = status; }

    public String getStatusName() { return statusName; }

    public static SerialStatus toNovelStatus(String statusName){
        return switch (statusName){
            case "연재중" -> SERIES;
            case "휴재중" -> RECESS;
            case "완결" -> COMPLETE;
            case "종료" -> TERMINATION;
            case "연재예정" -> SCHEDULE;
            default -> throw new WebNovelServiceException(NO_VALID_NOVEL_STATUS);
        };
    }

    public static String toStatusName(SerialStatus status){
        return switch (status){
            case SERIES -> SERIES.getStatusName();
            case RECESS -> RECESS.getStatusName();
            case COMPLETE -> COMPLETE.getStatusName();
            case TERMINATION -> TERMINATION.getStatusName();
            case SCHEDULE -> SCHEDULE.getStatusName();
        };
    }
}

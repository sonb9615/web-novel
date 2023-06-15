package numble.webnovel.novel.enums;

import numble.webnovel.exception.WebNovelServiceException;

import static numble.webnovel.exception.ErrorCode.NO_VALID_GENRE;

public enum Genre {

    ROMANCE("로맨스")
    ,HORROR("호러")
    ,DRAMA("드라마")
    ,ACTION("액션")

    ;
    private final String genreName;

    Genre(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName(){
        return genreName;
    }

    public static Genre getGenreCode(String genreName){
        return switch (genreName){
            case "로맨스" -> ROMANCE;
            case "호러" -> HORROR;
            case "드라마" -> DRAMA;
            case "액션" -> ACTION;
            default -> throw new WebNovelServiceException(NO_VALID_GENRE);
        };
    }

    // redis 에 저장될 키값을 key로 설정하는게 좋을 것 같은데, 해당 값에서 어떻게 처리할지는 차차 생각해보도록 하자

}

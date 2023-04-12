package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "novel_tag")
public class NovelTag {

  @Id
  @Column(name = "id")@GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "novel_id")
  private String novelId;

  @Column(name = "tag")
  private String tag;

  public static NovelTag novelTag(String novelId, String tag) {
    NovelTag novelTag = new NovelTag();
    novelTag.setNovelId(novelId);
    novelTag.setTag(tag);
    return novelTag;
  }
}

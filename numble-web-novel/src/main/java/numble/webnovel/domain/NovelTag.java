package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "novel_tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NovelTag {

  @Id
  @Column(name = "id")@GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "tag")
  private String tag;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "novel_id")
  private Novel novel;

  public void setNovel(Novel novel){
    this.novel = novel;
    novel.getNovelTagList().add(this);
  }

  public static NovelTag creatNovelTag(Novel novel, String tag) {
    NovelTag novelTag = new NovelTag();
    novelTag.setNovel(novel);
    novelTag.setTag(tag);
    return novelTag;
  }


}

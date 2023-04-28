package numble.webnovel.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "child_code")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChildCode {

  @Id
  @Column(name = "code")
  private String code;

  @Column(name = "value")
  private String value;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_code")
  private ParentCode parentCode;

  public void addParentCode(ParentCode code){
    this.parentCode = code;
    code.getChildCodeList().add(this);
  }

  public static ChildCode childCode(String code, String value, ParentCode parentCode){
    ChildCode childCode = new ChildCode();
    childCode.setCode(code);
    childCode.setParentCode(parentCode);
    childCode.setValue(value);
    return childCode;
  }


}

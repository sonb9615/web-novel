package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "child_code")
public class ChildCode {

  @Id
  @Column(name = "code")
  private String code;
  @Column(name = "parent_code")
  private String parentCode;
  @Column(name = "value")
  private String value;

  public static ChildCode childCode(String code, String parentCode, String value){
    ChildCode childCode = new ChildCode();
    childCode.setCode(code);
    childCode.setParentCode(parentCode);
    childCode.setValue(value);
    return childCode;
  }


}

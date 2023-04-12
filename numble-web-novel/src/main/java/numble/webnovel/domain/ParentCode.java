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
@Table(name = "parent_code")
public class ParentCode {

  @Id
  @Column(name = "code")
  private String code;

  @Column(name = "value")
  private String value;

  public static ParentCode parentCode(String code, String value) {
    ParentCode parentCode = new ParentCode();
    parentCode.setCode(code);
    parentCode.setValue(value);
    return parentCode;
  }
}

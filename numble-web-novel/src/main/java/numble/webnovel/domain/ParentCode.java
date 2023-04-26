package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "parent_code")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParentCode {

  @Id
  @Column(name = "code")
  private String code;

  @Column(name = "value")
  private String value;

  @OneToMany(mappedBy = "parentCode", cascade = CascadeType.ALL)
  private List<ChildCode> childCodeList = new ArrayList<>();

  public static ParentCode parentCode(String code, String value) {
    ParentCode parentCode = new ParentCode();
    parentCode.setCode(code);
    parentCode.setValue(value);
    return parentCode;
  }
}

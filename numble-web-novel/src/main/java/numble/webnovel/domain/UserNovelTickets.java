package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "user_novel_tickets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNovelTickets {

  @Id
  @Column(name = "ticket_no")
  private String ticketNo;

  @Column(name = "ticket_cnt")
  private long ticketCnt;

  @Column(name = "usable_ticket_cnt")
  private long usableTicketCnt;

  @Column(name = "used_ticket_cnt")
  private long usedTicketCnt;

  @Column(name = "ticket_cost")
  private long ticketCost;

  @Column(name = "reg_dt")
  private LocalDateTime regDt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_no")
  private UserInfo userInfo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "novel_id")
  private Novel novel;

  @OneToMany(mappedBy = "userNovelTickets")
  private List<UserLibrary> userLibraryList;

  public void setUserInfo(UserInfo info){
    this.userInfo = info;
    info.getUserNovelTickets().add(this);
  }

  //생성 매서드
  public static UserNovelTickets createUserNovelTickets(String ticketNo, long ticketCnt, long usableTicketCnt
          , long ticketCost, UserInfo userInfo, Novel novel) {
    UserNovelTickets userNovelTickets = new UserNovelTickets();
    userNovelTickets.setTicketNo(ticketNo);
    userNovelTickets.setTicketCnt(ticketCnt);
    userNovelTickets.setUsableTicketCnt(usableTicketCnt);
    userNovelTickets.setUsedTicketCnt(0);
    userNovelTickets.setTicketCost(ticketCost);
    userNovelTickets.setRegDt(LocalDateTime.now());
    userNovelTickets.setUserInfo(userInfo);
    userNovelTickets.setNovel(novel);
    return userNovelTickets;
  }

  // 비지니스 로직
  public String useTicket(){
    long remainTickets = this.getUsableTicketCnt() - 1;
    if(remainTickets < 0){
      throw new CommonException(ExceptionEnum.NOVEL_TICKET_NOT_EXIST_EXCEPTION);
    }
    this.setUsableTicketCnt(remainTickets);
    this.setUsedTicketCnt(this.getUsedTicketCnt() + 1);
    // 소설 구매수 + 1
    this.novel.setPaymentCnt(this.novel.getPaymentCnt() + 1);
    return this.getTicketNo();
  }

  public void chargeTicket(int ticketCnt){
    this.setUsableTicketCnt(ticketCnt);
    this.setUsedTicketCnt(0);
  }
}

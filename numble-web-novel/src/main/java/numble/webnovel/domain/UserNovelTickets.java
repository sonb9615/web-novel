package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "user_novel_tickets")
public class UserNovelTickets {

  @Id
  @Column(name = "ticket_no")
  private String ticketNo;

  @Column(name = "user_no")
  private String userNo;

  @Column(name = "novel_id")
  private String novelId;

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

  public static UserNovelTickets userNovelTickets(String ticketNo, String userNo, String novelId, long ticketCnt, long usableTicketCnt, long usedTicketCnt, long ticketCost) {
    UserNovelTickets userNovelTickets = new UserNovelTickets();
    userNovelTickets.setTicketNo(ticketNo);
    userNovelTickets.setUserNo(userNo);
    userNovelTickets.setNovelId(novelId);
    userNovelTickets.setTicketCnt(ticketCnt);
    userNovelTickets.setUsableTicketCnt(usableTicketCnt);
    userNovelTickets.setUsedTicketCnt(usedTicketCnt);
    userNovelTickets.setTicketCost(ticketCost);
    userNovelTickets.setRegDt(LocalDateTime.now());
    return userNovelTickets;
  }
}

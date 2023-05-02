package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "novel_ticket")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NovelTicket {

  @Id
  @Column(name = "ticket_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String ticketId;

  @Column(name = "ticket_cnt")
  private int ticketCnt;

  @Column(name = "usable_ticket_cnt")
  private int usableTicketCnt;

  @Column(name = "used_ticket_cnt")
  private int usedTicketCnt;

  @Column(name = "ticket_cost")
  private int ticketCost;

  @Column(name = "reg_dt")
  private LocalDateTime regDt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "novel_id")
  private Novel novel;

  @OneToMany(mappedBy = "novelTicket")
  private List<Library> libraryList;

  public void setMember(Member info){
    this.member = info;
    info.getNovelTickets().add(this);
  }

  //생성 매서드
  public static NovelTicket createNovelTicket(int ticketCnt, int usableTicketCnt
          , int ticketCost, Member member, Novel novel) {
    NovelTicket novelTicket = new NovelTicket();
    novelTicket.setTicketCnt(ticketCnt);
    novelTicket.setUsableTicketCnt(usableTicketCnt);
    novelTicket.setUsedTicketCnt(0);
    novelTicket.setTicketCost(ticketCost);
    novelTicket.setRegDt(LocalDateTime.now());
    novelTicket.setMember(member);
    novelTicket.setNovel(novel);
    return novelTicket;
  }

  // 비지니스 로직
  public String useTicket(){
    int remainTickets = this.getUsableTicketCnt() - 1;
    if(remainTickets < 0){
      throw new CommonException(ExceptionEnum.NOVEL_TICKET_NOT_EXIST_EXCEPTION);
    }
    this.setUsableTicketCnt(remainTickets);
    this.setUsedTicketCnt(this.getUsedTicketCnt() + 1);
    // 소설 구매수 + 1
    this.novel.setPaymentCnt(this.novel.getPaymentCnt() + 1);
    return this.getTicketId();
  }

  public void chargeTicket(int ticketCnt){
    this.setUsableTicketCnt(ticketCnt);
    this.setUsedTicketCnt(0);
  }
}

package numble.webnovel.novelTicket.repository;

import numble.webnovel.novelTicket.domain.NovelTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelTicketRepository extends JpaRepository<NovelTicket, Long> {
}

package numble.webnovel.repository.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeInfoRequest {

    private CacheChargeRequest cacheChargeRequest;
    private NovelTicketChargeRequest novelTicketChargeRequest;
}

package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeInfoRequest {

    private CacheCargeInfo cacheCargeInfo;
    private NovelTicketChargeInfo novelTicketChargeInfo;
}

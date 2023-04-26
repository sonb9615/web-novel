package numble.webnovel.repository.dto.response;

import lombok.Data;

@Data
public class ChargeTicketsResponse {
    private String result;
    private int chargeTicketCnt;

    public static ChargeTicketsResponse createChargeTicketsResponse(String result, int chargeTicketCnt) {
        ChargeTicketsResponse chargeApiResponse = new ChargeTicketsResponse();
        chargeApiResponse.setResult(result);
        chargeApiResponse.setChargeTicketCnt(chargeTicketCnt);
        return chargeApiResponse;
    }
}

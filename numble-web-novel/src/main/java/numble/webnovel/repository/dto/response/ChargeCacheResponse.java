package numble.webnovel.repository.dto.response;

import lombok.Data;

@Data
public class ChargeCacheResponse {
    private String result;
    private int chargeCost;

    public static ChargeCacheResponse createChargeApiResponse(String result, int chargeCost) {
        ChargeCacheResponse chargeApiResponse = new ChargeCacheResponse();
        chargeApiResponse.setResult(result);
        chargeApiResponse.setChargeCost(chargeCost);
        return chargeApiResponse;
    }
}

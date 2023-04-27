package numble.webnovel.repository.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CommonListRequest {

    private List<String> updateList;
    private List<String> deleteList;
}

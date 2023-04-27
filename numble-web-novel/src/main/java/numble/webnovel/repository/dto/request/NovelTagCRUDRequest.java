package numble.webnovel.repository.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class NovelTagCRUDRequest {

    // tag name : String
    private List<String> updateList;
    // existed tag id : Long
    private List<Long> deleteList;
}

package numble.webnovel.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDGeneration {

    public String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}

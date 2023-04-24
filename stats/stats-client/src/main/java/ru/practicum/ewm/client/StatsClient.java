package ru.practicum.ewm.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.dto.EndpointHitDto;

import java.util.List;
import java.util.Map;

@Service
public class StatsClient extends MyClient {

    @Autowired
    public StatsClient(@Value("http://stats-server:9090") String serverUrl, RestTemplateBuilder builder) {
        super(builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl)).requestFactory(HttpComponentsClientHttpRequestFactory::new).build());
    }

    public ResponseEntity<Object> hit(EndpointHitDto endpointHitDto) {
        return post("/hit", endpointHitDto);
    }

    public ResponseEntity<Object> getAllBookingByOwnerId(String start, String end, List<String> uris, boolean unique) {
        Map<String, Object> parameters = Map.of("start", start, "end", end, "uris", uris, "unique", unique);
        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", parameters);

    }


}

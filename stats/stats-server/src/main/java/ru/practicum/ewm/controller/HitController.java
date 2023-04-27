package ru.practicum.ewm.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.EndpointHitDto;
import ru.practicum.ewm.model.ViewStats;
import ru.practicum.ewm.service.HitService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HitController {


    private static final Logger log = LoggerFactory.getLogger(HitService.class);
    private final HitService hitService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public EndpointHitDto post(@Valid @RequestBody EndpointHitDto endpointHitDto) {
        log.info("Trying to post hitDto: " + endpointHitDto);
        return hitService.post(endpointHitDto);
    }

    @GetMapping("/stats")
    List<ViewStats> getStats(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                             @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                             @RequestParam(value = "uris", required = false, defaultValue = "") List<String> uris,
                             @RequestParam(value = "unique", required = false, defaultValue = "false") boolean unique) {
        log.info("Request stats with:" + start + end + uris + unique + " in controller");
        return hitService.getStats(start, end, uris, unique);
    }
}

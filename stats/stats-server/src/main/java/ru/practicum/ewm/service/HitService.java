package ru.practicum.ewm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.EndpointHitDto;
import ru.practicum.ewm.mapper.EndpointHitMapper;
import ru.practicum.ewm.model.EndpointHit;
import ru.practicum.ewm.model.ViewStats;
import ru.practicum.ewm.repository.HitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HitService {

    private static final Logger log = LoggerFactory.getLogger(HitRepository.class);

    private final HitRepository hitRepository;


    @Autowired
    public HitService(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    public EndpointHitDto post(EndpointHitDto endpointHitDto) {
        log.info("Trying to save:" + endpointHitDto);
        EndpointHit endpointHit = hitRepository.save(EndpointHitMapper.toEndpointHit(endpointHitDto));
        log.info("Success saving with id:" + endpointHit.getId());
        return EndpointHitMapper.toEndpointHitDto(endpointHit);
    }


    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        log.info("Request stats in service/repository");
        if (unique) {
            if (uris.isEmpty() || uris == null) {
                log.info("Request unique hits without uris");
                return hitRepository.getStatsNotUrisUnique(start, end);
            }
            log.info("Request unique hits with uris");
            return hitRepository.getStatsUnique(start, end, uris);
        }
        if (uris.isEmpty() || uris == null) {
            log.info("Request not unique hits without uris");
            return hitRepository.getStatsNotUrisNotUnique(start, end);
        }
        log.info("Request not unique hits with uris");
        return hitRepository.getStatsNotUnique(start, end, uris);
    }
}

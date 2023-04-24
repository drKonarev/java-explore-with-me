package ru.practicum.ewm.mapper;

import ru.practicum.ewm.dto.EndpointHitDto;
import ru.practicum.ewm.dto.ViewStatsDto;
import ru.practicum.ewm.model.EndpointHit;
import ru.practicum.ewm.model.ViewStats;

public class EndpointHitMapper {

    public static EndpointHitDto toEndpointHitDto(EndpointHit endpointHit) {
        return new EndpointHitDto(endpointHit.getId(),
                endpointHit.getTimestamp(),
                endpointHit.getApp(),
                endpointHit.getUri(),
                endpointHit.getIp()
        );
    }

    public static EndpointHit toEndpointHit(EndpointHitDto endpointHitDto) {
        return new EndpointHit(endpointHitDto.getId(),
                endpointHitDto.getApp(),
                endpointHitDto.getIp(),
                endpointHitDto.getUri(),
                endpointHitDto.getTimestamp()
        );
    }

    public static ViewStatsDto toViewStatsDto(ViewStats viewStats) {
        return new ViewStatsDto(viewStats.getApp(),
                viewStats.getUri(),
                viewStats.getHits()
        );
    }
}

package dev.pinecone.webapp.converter;

import dev.pinecone.webapp.entity.Consumer;
import dev.pinecone.webapp.entity.Place;
import dev.pinecone.webapp.model.dto.PlaceDto;
import dev.pinecone.webapp.model.request.PlaceCreateRequest;

public final class PlaceConverter {
    public static PlaceDto toDto(Place place) {
        return PlaceDto
                .builder()
                .id(place.getId())
                .name(place.getName())
                .city(place.getCity())
                .district(place.getDistrict())
                .locationUrl(place.getLocationUrl())
                .hasAC(place.isHasAC())
                .hasEthernet(place.isHasEthernet())
                .hasElectricity(place.isHasElectricity())
                .build();
    }

    public static Place toEntity(Consumer consumer, PlaceCreateRequest request) {
        return Place.builder()
                .city(request.getCity())
                .name(request.getName())
                .hasAC(request.isHasAc())
                .hasElectricity(request.isHasElectricity())
                .district(request.getDistrict())
                .consumer(consumer)
                .locationUrl(request.getLocationUrl())
                .placePath(request.getName() + request.getCity())
                .build();
    }
}

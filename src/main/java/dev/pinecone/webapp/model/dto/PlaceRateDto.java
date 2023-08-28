package dev.pinecone.webapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PlaceRateDto {
    private List<RateDto> rateDtoList;
    private PlaceDto placeDto;
}

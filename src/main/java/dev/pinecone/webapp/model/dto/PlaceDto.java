package dev.pinecone.webapp.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceDto {
    private Long id;
    private String name;
    private String locationUrl;
    private boolean hasAC;
    private boolean hasEthernet;
    private boolean hasElectricity;
    private String district;
    private String city;
}

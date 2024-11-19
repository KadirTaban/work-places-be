package dev.pinecone.webapp.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaceCreateRequest {

    @NotBlank(message = "place.create.request.name.notBlank")
    private String name;
    @NotBlank(message = "place.create.request.locationUrl.notBlank")
    private String locationUrl;
    @NotNull(message = "place.create.request.hasAC.notNull")
    private boolean hasAc;
    @NotNull(message = "place.create.request.hasEthernet.notNull")
    private boolean hasEthernet;
    @NotNull(message = "place.create.request.hasElectricity.notNull")
    private boolean hasElectricity;
    @NotBlank(message = "place.create.request.district.notBlank")
    private String district;
    @NotBlank(message = "place.create.request.city.notBlank")
    private String city;
}

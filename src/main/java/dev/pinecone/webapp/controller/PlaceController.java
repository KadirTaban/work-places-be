package dev.pinecone.webapp.controller;

import dev.pinecone.webapp.model.dto.PlaceRateDto;
import dev.pinecone.webapp.model.request.PlaceCreateRequest;
import dev.pinecone.webapp.service.PlaceService;
import dev.pinecone.webapp.utils.constants.Apis;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(Apis.Place.BASE_URL)
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{placeId}")
    public PlaceRateDto getPlaceRateDetail(@PathVariable Long placeId) {
        return placeService.getPlaceRateDetail(placeId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void create(@RequestParam Long consumerId,
                       @NotBlank(message = "place.create.request.name.notBlank") @RequestParam String name,
                       @NotBlank(message = "place.create.request.locationUrl.notBlank") @RequestParam String locationUrl,
                       @NotNull(message = "place.create.request.hasAC.notNull") @RequestParam boolean hasAc,
                       @NotNull(message = "place.create.request.hasEthernet.notNull") @RequestParam boolean hasEthernet,
                       @NotNull(message = "place.create.request.hasElectricity.notNull") @RequestParam boolean hasElectricity,
                       @NotBlank(message = "place.create.request.district.notBlank") @RequestParam String district,
                       @NotBlank(message = "place.create.request.city.notBlank") @RequestParam String city,
                       @RequestPart MultipartFile file) {
        final PlaceCreateRequest request = new PlaceCreateRequest();
        request.setName(name);
        request.setLocationUrl(locationUrl);
        request.setHasAc(hasAc);
        request.setHasEthernet(hasEthernet);
        request.setHasElectricity(hasElectricity);
        request.setDistrict(district);
        request.setCity(city);
        placeService.create(consumerId, request, file);
    }
}

package dev.pinecone.webapp.controller;

import dev.pinecone.webapp.model.dto.PlaceRateDto;
import dev.pinecone.webapp.model.request.PlaceCreateRequest;
import dev.pinecone.webapp.service.PlaceService;
import dev.pinecone.webapp.utils.constants.Apis;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Apis.Place.BASE_URL)
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{placeId}")
    public PlaceRateDto getPlaceRateDetail(@PathVariable Long placeId) {
        return placeService.getPlaceRateDetail(placeId);
    }

    @PostMapping
    public void create(@RequestBody PlaceCreateRequest request) {
        placeService.create(request);
    }
}

package dev.pinecone.webapp.controller;

import dev.pinecone.webapp.model.dto.PlaceRateDto;
import dev.pinecone.webapp.model.request.PlaceCreateRequest;
import dev.pinecone.webapp.service.PlaceService;
import dev.pinecone.webapp.utils.constants.Apis;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public void create(@RequestParam Long consumerId,
                       @Valid @RequestBody PlaceCreateRequest placeCreateRequest,
                       @RequestPart MultipartFile file) {
        placeService.create(consumerId, placeCreateRequest, file);
    }
}

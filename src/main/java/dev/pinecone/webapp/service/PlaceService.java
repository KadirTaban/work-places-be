package dev.pinecone.webapp.service;

import dev.pinecone.webapp.converter.PlaceConverter;
import dev.pinecone.webapp.converter.RateConverter;
import dev.pinecone.webapp.entity.Place;
import dev.pinecone.webapp.entity.Rate;
import dev.pinecone.webapp.entity.Consumer;
import dev.pinecone.webapp.model.dto.PlaceRateDto;
import dev.pinecone.webapp.model.dto.RateDto;
import dev.pinecone.webapp.model.request.PlaceCreateRequest;
import dev.pinecone.webapp.repository.ConsumerRepository;
import dev.pinecone.webapp.repository.PlaceRepository;
import dev.pinecone.webapp.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceConverter placeConverter;
    private final ConsumerRepository consumerRepository;
    private final RateRepository rateRepository;
    private final RateConverter rateConverter;

    public void create(Long consumerId, PlaceCreateRequest request) {

        Optional<Consumer> optionalConsumer = consumerRepository.findById(consumerId);
        if (optionalConsumer.isEmpty()) {
            throw new RuntimeException("Consumer not found");
        }

        final Consumer consumer = optionalConsumer.get();
        final Place place = placeConverter.toEntity(consumer, request);
        place.setConsumerId(consumer.getId());
        placeRepository.save(place);

    }

    public PlaceRateDto getPlaceRateDetail(Long placeId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Consumer> optionalConsumer = consumerRepository.findByEmail(email);
        if (optionalConsumer.isEmpty()) {
            throw new RuntimeException("Consumer not found");
        }
        Consumer consumer = optionalConsumer.get();
        final Place place = placeRepository.findById(placeId).orElseThrow();
        final List<Rate> rateList = rateRepository.findAllByPlaceId(placeId);

        final List<RateDto> rateDtoList = rateList.stream()
                .map(rate -> rateConverter.convertAsDto(rate))
                .collect(Collectors.toList());

        return PlaceRateDto.builder()
                .placeDto(placeConverter.toDto(place))
                .rateDtoList(rateDtoList)
                .build();
    }
}

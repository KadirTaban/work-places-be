package dev.pinecone.webapp.service;

import dev.pinecone.webapp.converter.PlaceConverter;
import dev.pinecone.webapp.converter.RateConverter;
import dev.pinecone.webapp.entity.Consumer;
import dev.pinecone.webapp.entity.Place;
import dev.pinecone.webapp.entity.Rate;
import dev.pinecone.webapp.model.dto.PlaceRateDto;
import dev.pinecone.webapp.model.dto.RateDto;
import dev.pinecone.webapp.model.request.PlaceCreateRequest;
import dev.pinecone.webapp.repository.ConsumerRepository;
import dev.pinecone.webapp.repository.PlaceRepository;
import dev.pinecone.webapp.repository.RateRepository;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dev.pinecone.webapp.converter.PlaceConverter.toDto;
import static dev.pinecone.webapp.converter.PlaceConverter.toEntity;
import static dev.pinecone.webapp.utils.constants.HeaderConstants.X_CONSUMER_ID;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final ConsumerRepository consumerRepository;
    private final RateRepository rateRepository;

    public void create(PlaceCreateRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        final Optional<Consumer> optionalConsumer = consumerRepository.findByEmail(email);
        optionalConsumer.ifPresent(consumer -> placeRepository.save(toEntity(consumer, request)));

    }

    public PlaceRateDto getPlaceRateDetail(Long placeId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Consumer> optionalConsumer = consumerRepository.findByEmail(email);
        if (optionalConsumer.isEmpty()) {
            throw new RuntimeException("Consumer not found");
        }
        final Consumer consumer = optionalConsumer.get();
        final Place place = placeRepository.findById(placeId).orElseThrow();
        place.setConsumer(consumer);
        final List<Rate> rateList = rateRepository.findAllByPlaceId(placeId);

        final List<RateDto> rateDtoList = rateList.stream()
                .map(RateConverter::convertAsDto)
                .collect(Collectors.toList());

        return PlaceRateDto.builder()
                .placeDto(toDto(place))
                .rateDtoList(rateDtoList)
                .build();
    }
}

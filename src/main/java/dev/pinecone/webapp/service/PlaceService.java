package dev.pinecone.webapp.service;

import dev.pinecone.webapp.converter.PlaceConverter;
import dev.pinecone.webapp.converter.RateConverter;
import dev.pinecone.webapp.entity.Place;
import dev.pinecone.webapp.entity.Rate;
import dev.pinecone.webapp.entity.User;
import dev.pinecone.webapp.model.dto.PlaceRateDto;
import dev.pinecone.webapp.model.dto.RateDto;
import dev.pinecone.webapp.model.request.PlaceCreateRequest;
import dev.pinecone.webapp.model.response.BaseResponse;
import dev.pinecone.webapp.repository.PlaceRepository;
import dev.pinecone.webapp.repository.RateRepository;
import dev.pinecone.webapp.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final RateRepository rateRepository;
    private final RateConverter rateConverter;

    public void create(PlaceCreateRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);

        placeRepository.save(placeConverter.toEntity(user.get(), request));

    }

    public PlaceRateDto getPlaceRateDetail(Long placeId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);
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

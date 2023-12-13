package dev.pinecone.webapp.service;

import dev.pinecone.webapp.entity.Place;
import dev.pinecone.webapp.entity.Rate;
import dev.pinecone.webapp.entity.Consumer;
import dev.pinecone.webapp.model.request.RateCreateRequest;
import dev.pinecone.webapp.repository.PlaceRepository;
import dev.pinecone.webapp.repository.RateRepository;
import dev.pinecone.webapp.repository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;
    private final ConsumerRepository consumerRepository;
    private final PlaceRepository placeRepository;


    public void create(RateCreateRequest rateCreateRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Consumer> consumer = consumerRepository.findByEmail(email);
        Optional<Place> place = placeRepository.findById(rateCreateRequest.getPlaceId());

        rateRepository.save(Rate.builder()
                                    .rate(rateCreateRequest.getRate())
                                    .criteria(rateCreateRequest.getRateCriteria())
                                    .place(place.get())
                                    .consumer(consumer.get())
                                    .build());
    }

    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }
}

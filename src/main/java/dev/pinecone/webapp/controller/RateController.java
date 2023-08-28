package dev.pinecone.webapp.controller;

import dev.pinecone.webapp.entity.Rate;
import dev.pinecone.webapp.model.request.RateCreateRequest;
import dev.pinecone.webapp.service.RateService;
import dev.pinecone.webapp.utils.constants.Apis;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Apis.Rate.BASE_URL)
public class RateController {

    private final RateService rateService;

    @GetMapping
    public List<Rate> getAllRates(){
        return rateService.getAllRates();
    }

    @PostMapping
    public void create(RateCreateRequest rateCreateRequest) {
        rateService.create(rateCreateRequest);
    }

}

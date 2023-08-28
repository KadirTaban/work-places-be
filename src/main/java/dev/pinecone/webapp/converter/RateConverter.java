package dev.pinecone.webapp.converter;

import dev.pinecone.webapp.entity.Rate;
import dev.pinecone.webapp.model.dto.RateDto;
import org.springframework.stereotype.Component;

@Component
public class RateConverter {

    public RateDto convertAsDto(Rate rate) {
        return RateDto.builder()
                .id(rate.getId())
                .rateCriteria(rate.getCriteria())
                .rate(rate.getRate())
                .build();
    }

}

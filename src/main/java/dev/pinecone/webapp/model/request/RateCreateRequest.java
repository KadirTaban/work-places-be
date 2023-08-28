package dev.pinecone.webapp.model.request;

import dev.pinecone.webapp.entity.enums.CriteriaRate;
import lombok.Data;

@Data
public class RateCreateRequest {

    private Double rate;
    private Long placeId;
    private CriteriaRate rateCriteria;

}

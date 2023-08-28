package dev.pinecone.webapp.model.dto;

import dev.pinecone.webapp.entity.enums.CriteriaRate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RateDto {

    private Long id;

    private Double rate;

    private CriteriaRate rateCriteria;


}

package dev.pinecone.webapp.repository;

import dev.pinecone.webapp.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate,Long> {
    List<Rate> findAllByPlaceId(Long placeId);
}

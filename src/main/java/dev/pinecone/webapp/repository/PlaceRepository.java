package dev.pinecone.webapp.repository;

import dev.pinecone.webapp.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place,Long> {

}

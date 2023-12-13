package dev.pinecone.webapp.repository;

import dev.pinecone.webapp.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer,Long> {
    boolean existsByEmail(String email);
    Optional<Consumer> findByEmail(String email);
}

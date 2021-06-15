package com.staszic.poll.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByUniqueId(UUID uniqueId);

    List<Image> findAllByOrderByVotesDesc();

}

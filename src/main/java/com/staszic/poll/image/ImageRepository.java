package com.staszic.poll.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByAuthor(String author);

    List<Image> findAllByOrderByVotesDesc();

}

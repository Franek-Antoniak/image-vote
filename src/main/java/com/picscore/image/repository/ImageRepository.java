package com.picscore.image.repository;

import com.picscore.image.Image;
import com.picscore.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByUniqueId(UUID uniqueId);

	Optional<Image> findByAuthorAndUniqueId(User author, UUID uniqueId);
}

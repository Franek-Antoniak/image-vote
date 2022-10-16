package com.highschool.image.vote.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.highschool.image.vote.user.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Entity
@Table(name = "Images")
public class Image {
	private final UUID uniqueId = UUID.randomUUID();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer votes = 0;
	@ManyToOne
	private User author;
	private String path;
}




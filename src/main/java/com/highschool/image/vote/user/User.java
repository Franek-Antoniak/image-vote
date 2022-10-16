package com.highschool.image.vote.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final UUID uniqueId = UUID.randomUUID();
	private Integer votes = 3;
	private String name;
	private boolean castVote = false;
	private HashSet<UUID> hashSet = new HashSet<>();

	public boolean canVote() {
		return votes > 0;
	}
}




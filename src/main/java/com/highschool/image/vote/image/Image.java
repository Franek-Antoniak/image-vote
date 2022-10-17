package com.highschool.image.vote.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.highschool.image.vote.user.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Images")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Builder.Default
	private UUID uniqueId = UUID.randomUUID();
	@Builder.Default
	private Integer votes = 0;
	private String fileName;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "votes")
	@ToString.Exclude
	private List<User> voters = new ArrayList<>();
	@ManyToOne(optional = false)
	private User author;

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		Image image = (Image) o;
		return id != null && Objects.equals(id, image.id);
	}
}




package com.highschool.image.vote.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.highschool.image.vote.user.User;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
	@Type(type = "uuid-char")
	@Builder.Default
	private UUID uniqueId = UUID.randomUUID();
	private String fileName;
	@ManyToMany(mappedBy = "votes", fetch = FetchType.EAGER)
	@ToString.Exclude
	@Builder.Default
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




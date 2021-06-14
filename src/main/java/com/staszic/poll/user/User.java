package com.staszic.poll.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID uniqueId;
    private Integer votes = 3;
    private String name;
    private boolean castVote = false;
    private HashSet<UUID> hashSet = new HashSet<>();

    public boolean vote() {
        if(votes > 0)
            votes--;
        else
            return false;
        return true;
    }

    @PrePersist
    private void onCreate() {
        uniqueId = java.util.UUID.randomUUID();
    }
}




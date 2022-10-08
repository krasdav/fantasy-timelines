package com.github.fantasytimelines.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String password;
    private boolean active = false;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private final List<Timeline> timelines = new ArrayList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public void addTimelines(Timeline... timelines){
        this.timelines.addAll(List.of(timelines));
    }
}

package com.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private Boolean enabled;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private List<UserAuthorities> userAuthorities = new ArrayList<>();

    @Builder
    public User(String username,String password,Boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userAuthorities = new ArrayList<>();
    }
}

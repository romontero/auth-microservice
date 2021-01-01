package com.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_authorities")
@NoArgsConstructor
public class UserAuthorities implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @NonNull
    private Authority authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @NonNull
    @JsonBackReference
    private User user;

    @Builder
    public UserAuthorities(Authority authority, User user) {
        this.authority = authority;
        this.user = user;
    }
}

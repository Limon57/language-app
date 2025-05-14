package com.example.languageapp.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portugueseId")
    private PortugueseModel portugueseModel;

    public User(PortugueseModel portugueseModel) {
        this.portugueseModel = portugueseModel;
    }

}


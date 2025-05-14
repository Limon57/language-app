package com.example.languageapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "library")
public class PortugueseLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalWords;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Word> words;

}

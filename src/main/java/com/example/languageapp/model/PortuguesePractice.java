package com.example.languageapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "practice")
public class PortuguesePractice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double score;
    private LocalDateTime date;
    private int mistakes;
    private int duration;
    private int wordsPracticed;
    private String practiceType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "practice_words",
            joinColumns = @JoinColumn(name = "practice_id"),     // FK to PortuguesePractice
            inverseJoinColumns = @JoinColumn(name = "word_id")   // FK to Word
    )

    private List<Word> words;

    @ManyToOne
    @JoinColumn(name = "portuguese_model_id")
    private PortugueseModel portugueseModel;

}

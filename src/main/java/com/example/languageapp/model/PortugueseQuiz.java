package com.example.languageapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "quiz")
public class PortugueseQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double score; // changed to match PortuguesePractice
    private int level;
    private LocalDateTime date;
    private int mistakes;
    private int duration;
    private boolean pass;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "quiz_words",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private List<Word> words;

    @ManyToOne
    @JoinColumn(name = "portuguese_model_id")
    private PortugueseModel portugueseModel;
}

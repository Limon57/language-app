package com.example.languageapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "portuguese")
public class PortugueseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int userLevel = 0;
    private int userStreak = 0;

    @OneToOne
    @JoinColumn
    private PortugueseLibrary portugueseLibrary;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "portuguese_model_id") // foreign key in PortuguesePractice
    private List<PortuguesePractice> portuguesePracticeList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "portuguese_model_id") // foreign key in PortugueseQuiz
    private List<PortugueseQuiz> portugueseQuizList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Word> vault; // shared word set

}

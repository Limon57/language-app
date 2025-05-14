package com.example.languageapp.repository;


import com.example.languageapp.model.PortuguesePractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PortuguesePracticeRepository extends JpaRepository<PortuguesePractice, Long> {


    List<PortuguesePractice> findAllByScoreBetween(double minScore, double maxScore);
    List<PortuguesePractice> findAllByDateBetween(LocalDateTime minDate, LocalDateTime maxDate);
    List<PortuguesePractice> findAllByMistakesBetween(int minMistakes, int maxMistakes);
    List<PortuguesePractice> findAllByDurationBetween(int minDuration, int maxDuration);
    List<PortuguesePractice> findAllByWordsPracticedBetween(int minWordsPracticed, int maxWordsPracticed);
    List<PortuguesePractice> findAllByPracticeType(String practiceType);

}

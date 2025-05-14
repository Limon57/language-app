package com.example.languageapp.service;

import com.example.languageapp.dto.portuguesePractice.PortuguesePracticeRequestDTO;
import com.example.languageapp.model.PortugueseModel;
import com.example.languageapp.model.PortuguesePractice;
import com.example.languageapp.model.Word;
import com.example.languageapp.repository.PortugueseModelRepository;
import com.example.languageapp.repository.PortuguesePracticeRepository;
import com.example.languageapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PortuguesePracticeService {

    private final PortuguesePracticeRepository portuguesePracticeRepository;
    private final PortugueseModelRepository portugueseModelRepository;
    private final WordRepository wordRepository;

    @Autowired
    public PortuguesePracticeService(PortuguesePracticeRepository portuguesePracticeRepository,
                                     PortugueseModelRepository portugueseModelRepository,
                                     WordRepository wordRepository) {
        this.portuguesePracticeRepository = portuguesePracticeRepository;
        this.portugueseModelRepository = portugueseModelRepository;
        this.wordRepository = wordRepository;
    }

    public PortuguesePractice saveFromDTO(PortuguesePracticeRequestDTO dto) {
        PortuguesePractice practice = new PortuguesePractice();
        practice.setScore(dto.getScore());
        practice.setMistakes(dto.getMistakes());
        practice.setDuration(dto.getDuration());
        practice.setWordsPracticed(dto.getWordsPracticed());
        practice.setPracticeType(dto.getPracticeType());
        practice.setDate(LocalDateTime.now());

        PortugueseModel model = portugueseModelRepository.findById(dto.getPortugueseModelId())
                .orElseThrow(() -> new RuntimeException("Portuguese model not found"));
        practice.setPortugueseModel(model);

        List<Word> words = wordRepository.findAllById(dto.getWordIds());
        practice.setWords(words);

        return portuguesePracticeRepository.save(practice);
    }

    public PortuguesePractice findById(Long id){
        return portuguesePracticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PortuguesePractice not found"));
    }

    public void deleteById(Long id){
        portuguesePracticeRepository.deleteById(id);
    }

    public List<PortuguesePractice> findAllByScoreRange(double min, double max) {
        return portuguesePracticeRepository.findAllByScoreBetween(min, max);
    }

    public List<PortuguesePractice> findAllByDateRange(LocalDateTime min, LocalDateTime max){
        return portuguesePracticeRepository.findAllByDateBetween(min, max);
    }

    public List<PortuguesePractice> findAllByMistakeRange(int min, int max){
        return portuguesePracticeRepository.findAllByMistakesBetween(min, max);
    }

    public List<PortuguesePractice> findAllByDurationRange(int min, int max){
        return portuguesePracticeRepository.findAllByDurationBetween(min, max);
    }

    public List<PortuguesePractice> findAllByWordsPracticedRange(int min, int max){
        return portuguesePracticeRepository.findAllByWordsPracticedBetween(min, max);
    }

    public List<PortuguesePractice> findAllByPracticeType(String type){
        return portuguesePracticeRepository.findAllByPracticeType(type);
    }
}

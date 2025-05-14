package com.example.languageapp.service;

import com.example.languageapp.dto.word.WordRequestDTO;
import com.example.languageapp.model.Word;
import com.example.languageapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word findById(Long id) {
        return wordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Word not found"));
    }

    public Word saveFromDTO(WordRequestDTO dto) {
        Word word = new Word();
        word.setCategory(dto.getCategory());
        word.setTranslation(dto.getTranslation());
        word.setPronunciation(dto.getPronunciation());
        word.setOccurrence(dto.getOccurrence());
        word.setRightCounter(0);
        word.setWrongCounter(0);
        word.setSuccessRate(0);
        return wordRepository.save(word);
    }

    public void deleteById(Long id) {
        wordRepository.deleteById(id);
    }

    public List<Word> findAllByCategory(String category) {
        return wordRepository.findByCategory(category);
    }

    public List<Word> findAllByOccurrence(String occurrence) {
        return wordRepository.findByOccurrence(occurrence);
    }

    public List<Word> findAllByTranslation(String translation) {
        return wordRepository.findByTranslation(translation);
    }

    public List<Word> findAllBySuccessRateBetween(double min, double max) {
        return wordRepository.findBySuccessRateBetween(min, max);
    }

    public void increaseSuccessRate(Long wordId) {
        Word word = findById(wordId);
        word.setRightCounter(word.getRightCounter() + 1);
        updateSuccessRate(word);
    }

    public void decreaseSuccessRate(Long wordId) {
        Word word = findById(wordId);
        word.setWrongCounter(word.getWrongCounter() + 1);
        updateSuccessRate(word);
    }

    private void updateSuccessRate(Word word) {
        double total = word.getRightCounter() + word.getWrongCounter();
        word.setSuccessRate(total > 0 ? word.getRightCounter() / total : 0);
        wordRepository.save(word);
    }

    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    public void resetCounters(Long wordId) {
        Word word = findById(wordId);
        word.setRightCounter(0);
        word.setWrongCounter(0);
        word.setSuccessRate(0);
        wordRepository.save(word);
    }
}

package com.example.languageapp.controller;

import com.example.languageapp.dto.word.WordDetailDTO;
import com.example.languageapp.dto.word.WordRequestDTO;
import com.example.languageapp.dto.word.WordResponseDTO;
import com.example.languageapp.model.Word;
import com.example.languageapp.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/{id}")
    public WordDetailDTO getWordById(@PathVariable Long id) {
        return toDetailDTO(wordService.findById(id));
    }

    @PostMapping
    public WordResponseDTO saveWord(@RequestBody WordRequestDTO dto) {
        Word saved = wordService.saveFromDTO(dto);
        return toResponseDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteWord(@PathVariable Long id) {
        wordService.deleteById(id);
    }

    @GetMapping
    public List<WordResponseDTO> getAllWords() {
        return wordService.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<WordResponseDTO> getWordsByCategory(@RequestParam String category) {
        return wordService.findAllByCategory(category).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/occurrence")
    public List<WordResponseDTO> getWordsByOccurrence(@RequestParam String occurrence) {
        return wordService.findAllByOccurrence(occurrence).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/translation")
    public List<WordResponseDTO> getWordsByTranslation(@RequestParam String translation) {
        return wordService.findAllByTranslation(translation).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/success-rate")
    public List<WordResponseDTO> getWordsBySuccessRate(@RequestParam double min, @RequestParam double max) {
        return wordService.findAllBySuccessRateBetween(min, max).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @PutMapping("/{id}/success/increase")
    public void increaseSuccessRate(@PathVariable Long id) {
        wordService.increaseSuccessRate(id);
    }

    @PutMapping("/{id}/success/decrease")
    public void decreaseSuccessRate(@PathVariable Long id) {
        wordService.decreaseSuccessRate(id);
    }

    @PutMapping("/{id}/reset")
    public void resetWordStats(@PathVariable Long id) {
        wordService.resetCounters(id);
    }

    // Mappers
    private WordResponseDTO toResponseDTO(Word word) {
        WordResponseDTO dto = new WordResponseDTO();
        dto.setId(word.getId());
        dto.setCategory(word.getCategory());
        dto.setTranslation(word.getTranslation());
        dto.setPronunciation(word.getPronunciation());
        dto.setOccurrence(word.getOccurrence());
        dto.setSuccessRate(word.getSuccessRate());
        return dto;
    }

    private WordDetailDTO toDetailDTO(Word word) {
        WordDetailDTO dto = new WordDetailDTO();
        dto.setId(word.getId());
        dto.setCategory(word.getCategory());
        dto.setTranslation(word.getTranslation());
        dto.setPronunciation(word.getPronunciation());
        dto.setOccurrence(word.getOccurrence());
        dto.setRightCounter(word.getRightCounter());
        dto.setWrongCounter(word.getWrongCounter());
        dto.setSuccessRate(word.getSuccessRate());
        return dto;
    }
}

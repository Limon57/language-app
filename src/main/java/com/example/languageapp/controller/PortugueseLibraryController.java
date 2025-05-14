package com.example.languageapp.controller;

import com.example.languageapp.dto.portugueseLibrary.PortugueseLibraryRequestDTO;
import com.example.languageapp.dto.portugueseLibrary.PortugueseLibraryResponseDTO;
import com.example.languageapp.model.PortugueseLibrary;
import com.example.languageapp.model.Word;
import com.example.languageapp.service.PortugueseLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portuguese-library")
public class PortugueseLibraryController {

    private final PortugueseLibraryService portugueseLibraryService;

    @Autowired
    public PortugueseLibraryController(PortugueseLibraryService portugueseLibraryService) {
        this.portugueseLibraryService = portugueseLibraryService;
    }

    @GetMapping("/{id}")
    public PortugueseLibraryResponseDTO getLibrary(@PathVariable Long id){
        PortugueseLibrary library = portugueseLibraryService.findById(id);
        return toResponseDTO(library);
    }

    @PostMapping
    public PortugueseLibraryResponseDTO createLibrary(@RequestBody PortugueseLibraryRequestDTO dto){
        PortugueseLibrary saved = portugueseLibraryService.createOrUpdateLibrary(dto);
        return toResponseDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteLibrary(@PathVariable Long id){
        portugueseLibraryService.deleteLibrary(id);
    }

    @GetMapping("/{id}/words")
    public List<Word> getWords(@PathVariable Long id){
        return portugueseLibraryService.getWords(id);
    }

    @PutMapping("/{id}/add-word")
    public void addWord(@PathVariable Long id, @RequestBody Word word){
        portugueseLibraryService.addWord(id, word);
    }

    @PutMapping("/{id}/remove-word")
    public void removeWord(@PathVariable Long id, @RequestBody Word word){
        portugueseLibraryService.removeWord(id, word);
    }

    @PutMapping("/{id}/update-count")
    public void updateWordCount(@PathVariable Long id){
        portugueseLibraryService.updateTotalWords(id);
    }

    private PortugueseLibraryResponseDTO toResponseDTO(PortugueseLibrary lib){
        PortugueseLibraryResponseDTO dto = new PortugueseLibraryResponseDTO();
        dto.setId(lib.getId());
        dto.setTotalWords(lib.getWords() != null ? lib.getWords().size() : 0);
        return dto;
    }
}

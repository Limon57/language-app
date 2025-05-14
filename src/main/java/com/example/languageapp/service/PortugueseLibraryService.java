package com.example.languageapp.service;

import com.example.languageapp.dto.portugueseLibrary.PortugueseLibraryRequestDTO;
import com.example.languageapp.model.PortugueseLibrary;
import com.example.languageapp.model.Word;
import com.example.languageapp.repository.PortugueseLibraryRepository;
import com.example.languageapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortugueseLibraryService {

    private final PortugueseLibraryRepository portugueseLibraryRepository;
    private final WordRepository wordRepository;

    @Autowired
    public PortugueseLibraryService(PortugueseLibraryRepository portugueseLibraryRepository,
                                    WordRepository wordRepository) {
        this.portugueseLibraryRepository = portugueseLibraryRepository;
        this.wordRepository = wordRepository;
    }

    public PortugueseLibrary findById(Long id){
        return portugueseLibraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PortugueseLibrary not found"));
    }

    public PortugueseLibrary createOrUpdateLibrary(PortugueseLibraryRequestDTO dto){
        PortugueseLibrary library = new PortugueseLibrary();
        List<Word> words = wordRepository.findAllById(dto.getWordIds());
        library.setWords(words);
        library.setTotalWords(words.size());
        return portugueseLibraryRepository.save(library);
    }

    public void deleteLibrary(Long id){
        PortugueseLibrary library = findById(id);
        portugueseLibraryRepository.delete(library);
    }

    public List<Word> getWords(Long id){
        return findById(id).getWords();
    }

    public void addWord(Long id, Word word){
        PortugueseLibrary library = findById(id);
        List<Word> words = library.getWords();
        words.add(word);
        portugueseLibraryRepository.save(library);
    }

    public void removeWord(Long id, Word word){
        PortugueseLibrary library = findById(id);
        List<Word> words = library.getWords();
        words.remove(word);
        portugueseLibraryRepository.save(library);
    }

    public void updateTotalWords(Long id){
        PortugueseLibrary library = findById(id);
        library.setTotalWords(library.getWords().size());
        portugueseLibraryRepository.save(library);
    }
}

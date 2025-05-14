package com.example.languageapp.dto.portugueseLibrary;

import lombok.Data;
import java.util.List;

@Data
public class PortugueseLibraryRequestDTO {
    private List<Long> wordIds; // client just sends IDs
}

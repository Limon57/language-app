package com.example.languageapp.dto.portugueseModel;
import lombok.Data;

import java.util.List;

@Data
public class PortugueseModelRequestDTO {

    private Long portugueseLibraryId;
    private List<Long> wordIds;

}

package com.example.mywebsite.controller;

import com.example.mywebsite.model.DictionaryWord;
import com.example.mywebsite.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// Import CrossOrigin if needed for local dev with Vue CLI server, or handle with global config
// import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RestController
@RequestMapping("/api/dictionary")
// If running Vue dev server on a different port, CORS will be needed.
// Option 1: Add @CrossOrigin here (e.g., @CrossOrigin(origins = "http://localhost:8081"))
// Option 2: Configure global CORS (recommended for multiple controllers) - see commented code in MyWebsiteApplication or a WebMvcConfigurer bean.
// For now, let's assume global CORS or proxy setup by user if needed for local dev.
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/{word}")
    public ResponseEntity<DictionaryWord> getWordDefinition(@PathVariable String word) {
        Optional<DictionaryWord> dictionaryWordOptional = dictionaryService.findWord(word);

        // Use orElseThrow for cleaner not found handling if you want to return 404 directly
        // return dictionaryWordOptional.map(ResponseEntity::ok)
        // .orElse(ResponseEntity.notFound().build());

        if (dictionaryWordOptional.isPresent()) {
            return ResponseEntity.ok(dictionaryWordOptional.get());
        } else {
            // Consider what to return for not found: 404 or an empty body with 200 OK.
            // Returning 404 is generally more standard for REST APIs when a resource is not found.
            return ResponseEntity.notFound().build();
        }
    }
}

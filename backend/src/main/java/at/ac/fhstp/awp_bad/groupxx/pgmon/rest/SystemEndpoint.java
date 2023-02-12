package at.ac.fhstp.awp_bad.groupxx.pgmon.rest;

import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.LanguageDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/system")
public class SystemEndpoint {

    public final LanguageService languageService;

    public SystemEndpoint(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping(value = "/languages")
    public List<LanguageDto> getLanguages() {
        try {
            return languageService.getAllLanguages();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No language found", e);
        }
    }

}

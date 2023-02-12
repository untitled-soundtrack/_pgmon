package at.ac.fhstp.awp_bad.groupxx.pgmon.service.impl;

import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.LanguageDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Language;
import at.ac.fhstp.awp_bad.groupxx.pgmon.persistence.LanguageRepository;
import at.ac.fhstp.awp_bad.groupxx.pgmon.persistence.UserRepository;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.LanguageService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }

    @Override
    public List<LanguageDto> getAllLanguages() throws HttpClientErrorException {
        Iterable<Language> languages = languageRepository.findAll();
        List<LanguageDto> languageDtos = new ArrayList<>();

        for(Language singleLanguage : languages){
            LanguageDto languageDto = new LanguageDto();
            languageDto.setLanguage(singleLanguage.getLanguage());

            languageDtos.add(languageDto);
        }

        return languageDtos;
    }
}

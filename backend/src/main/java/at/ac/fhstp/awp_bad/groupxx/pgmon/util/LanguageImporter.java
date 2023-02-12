package at.ac.fhstp.awp_bad.groupxx.pgmon.util;

import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Language;
import at.ac.fhstp.awp_bad.groupxx.pgmon.persistence.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LanguageImporter {
    private static final Logger logger = LoggerFactory.getLogger(PokemonDataImporter.class);

    private final LanguageRepository languageRepository;

    public LanguageImporter(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }

    @PostConstruct
    public void addLanguage() {
        logger.info("Language import ...");
        Language language_de = new Language();
        language_de.setLanguage("de");
        languageRepository.save(language_de);

        Language language_en = new Language();
        language_en.setLanguage("en");
        languageRepository.save(language_en);



    }
}

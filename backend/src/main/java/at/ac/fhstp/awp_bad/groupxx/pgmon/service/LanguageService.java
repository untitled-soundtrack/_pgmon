package at.ac.fhstp.awp_bad.groupxx.pgmon.service;

import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.LanguageDto;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface LanguageService {
    List<LanguageDto> getAllLanguages() throws HttpClientErrorException;
}

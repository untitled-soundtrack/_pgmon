package at.ac.fhstp.awp_bad.groupxx.pgmon.service;

import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.NewSightingDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.SightingDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Sighting;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface SightingService {
    SightingDto getSighting(Integer sightingId) throws HttpClientErrorException;

    List<SightingDto> getSightingsByGeo(Double north, Double east, Double south, Double west);

    Sighting patchSightings (Integer sightingId, SightingDto sightingsDto) throws HttpClientErrorException.BadRequest;

    SightingDto addSighting(NewSightingDto newSightingDto) throws BadCredentialsException;
}

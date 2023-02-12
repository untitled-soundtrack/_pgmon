package at.ac.fhstp.awp_bad.groupxx.pgmon.service.impl;

import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.NewSightingDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.PositionDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.SightingDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Sighting;
import at.ac.fhstp.awp_bad.groupxx.pgmon.persistence.SightingRepository;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.SightingService;
import at.ac.fhstp.awp_bad.groupxx.pgmon.util.PokemonDataImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.text.Position;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SightingServiceImpl implements SightingService {
    private final SightingRepository sightingRepository;
    private static final Logger logger = LoggerFactory.getLogger(PokemonDataImporter.class);

    public SightingServiceImpl(SightingRepository sightingRepository) {
        this.sightingRepository = sightingRepository;
    }

    @Override
    public SightingDto getSighting(Integer sightingId) throws HttpClientErrorException {
        if (!sightingRepository.existsById(sightingId)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Sighting not found");
        }
        SightingDto sightingDto = new SightingDto();
        sightingRepository.findById(sightingId).ifPresent(
                sighting -> {
                    sightingDto.setSightingId(sighting.getSightingId());
                    sightingDto.setPokedexId(sighting.getPokedexId());
                    sightingDto.getPosition().setLat(sighting.getLat());
                    sightingDto.getPosition().setLng(sighting.getLng());
                    sightingDto.setTimestamp(sighting.getTimestamp());
                }
        );

        return sightingDto;
    }

    @Override
    public List<SightingDto> getSightingsByGeo(Double north, Double east, Double south, Double west) {
        List<Sighting> sightings = sightingRepository.getSightingsByGeo(north, east, south, west);

        List<SightingDto> sightingDtos = new ArrayList<>();

        for (Sighting sighting : sightings) {
            SightingDto sightingDto = new SightingDto();
            sightingDto.setPokedexId(sighting.getPokedexId());
            sightingDto.setTimestamp(sighting.getTimestamp());
            sightingDto.setSightingId(sighting.getSightingId());

            PositionDto positionDto = new PositionDto();
            positionDto.setLat(sighting.getLat());
            positionDto.setLng(sighting.getLng());

            sightingDto.setPosition(positionDto);

            sightingDtos.add(sightingDto);
        }

        return sightingDtos;
    }

    @Override
    public Sighting patchSightings(Integer sightingId, SightingDto sightingDto) throws HttpClientErrorException.BadRequest {
        Optional<Sighting> sighting = sightingRepository.findById(sightingId);

        if (!sightingRepository.existsById(sightingId)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "No Sighting found to patch");
        } else {
            if (sighting.isPresent()) {
                Sighting newSighting = sighting.get();
                newSighting.setSightingId(sightingId);
                newSighting.setLng(sightingDto.getPosition().getLng());
                newSighting.setLat(sightingDto.getPosition().getLat());
                newSighting.setTimestamp(sightingDto.getTimestamp());
                newSighting.setPokedexId(sightingDto.getPokedexId());
                return sightingRepository.save(newSighting);
            }
            return null;
        }

    }
    public SightingDto addSighting(NewSightingDto newSightingDto){

        Sighting sighting = new Sighting();
        //position entity mit sightings dto befüllen
        sighting.setTimestamp(Instant.now());
        sighting.setPokedexId(newSightingDto.getPokedexId());
        sighting.setLat(newSightingDto.getPosition().getLat());
        sighting.setLng(newSightingDto.getPosition().getLng());



        Sighting storedSighting = sightingRepository.save(sighting);
        SightingDto returnSightingDto = new SightingDto();
        returnSightingDto.setSightingId(storedSighting.getSightingId());
        returnSightingDto.setPokedexId(storedSighting.getPokedexId());
        returnSightingDto.setTimestamp(storedSighting.getTimestamp());
        returnSightingDto.getPosition().setLat(storedSighting.getLat());
        returnSightingDto.getPosition().setLng(storedSighting.getLng());

        return returnSightingDto;

    }
}

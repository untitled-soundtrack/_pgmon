package at.ac.fhstp.awp_bad.groupxx.pgmon.rest;

import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.NewSightingDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.SightingDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Sighting;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.SightingService;
import at.ac.fhstp.awp_bad.groupxx.pgmon.util.PokemonDataImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/sightings")
public class SightingEndpoint {

    public final SightingService sightingService;
    private static final Logger logger = LoggerFactory.getLogger(PokemonDataImporter.class);

    public SightingEndpoint(SightingService sightingService) {
        this.sightingService = sightingService;
    }

    @GetMapping(value = "/{sighting-id}")
    public SightingDto getSightingByID(@PathVariable("sighting-id") String sightingId) {
        SightingDto sightingDto;
        try {
            sightingDto = sightingService.getSighting(Integer.valueOf(sightingId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Sighting by this ID found", e);
        }
        return sightingDto;
    }

    @GetMapping(value = "/map/{north},{east},{south},{west}")
    public List<SightingDto> getSightingsByGeo(@PathVariable("north") String north,
                                               @PathVariable("east") String east,
                                               @PathVariable("south") String south,
                                               @PathVariable("west") String west) {
        try {
            return sightingService.getSightingsByGeo(
                    Double.valueOf(north), Double.valueOf(east), Double.valueOf(south), Double.valueOf(west)
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/{sighting-id}")
    public String patchSightings(@PathVariable("sighting-id") Integer sightingId, @RequestBody SightingDto sightingDto) {
        sightingDto.setTimestamp(Instant.now());
        try {
            sightingService.patchSightings(sightingId, sightingDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "no / wrong auth token");
        }
        return "Sighting updated succesfully!";
    }

    @PostMapping(value = "/")
    public SightingDto addSighting(@RequestBody NewSightingDto newSightingDto){

        try {
            return sightingService.addSighting(newSightingDto);
        }catch (BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials!");
        }

    }
}

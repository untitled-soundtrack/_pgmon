package at.ac.fhstp.awp_bad.groupxx.pgmon.rest;

import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.PokemonDetailsDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.PokemonDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Species;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.SpeciesService;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.impl.SpeciesServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonEndpoint {

    private final SpeciesService speciesService;

    public PokemonEndpoint(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @GetMapping(path = "/{language}")
    public List<PokemonDto> getAllPokemon(@PathVariable("language") String language) {
        try {
            return speciesService.getAllPokemon(language);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{language}/{pokedex-id}")
    public PokemonDetailsDto getPokemonBySpeciesId(@PathVariable("language") String language, @PathVariable("pokedex-id") String pokedexId) {
        try {
            return speciesService.getPokemonBySpeciesId(Integer.parseInt(pokedexId), language);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}

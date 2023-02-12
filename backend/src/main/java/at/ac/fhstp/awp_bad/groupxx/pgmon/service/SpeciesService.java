package at.ac.fhstp.awp_bad.groupxx.pgmon.service;

import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.PokemonDetailsDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.PokemonDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Species;

import java.util.List;

public interface SpeciesService {

    List<PokemonDto> getAllPokemon(String language) throws Exception;

    PokemonDetailsDto getPokemonBySpeciesId(Integer id, String language);
}

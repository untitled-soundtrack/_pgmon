package at.ac.fhstp.awp_bad.groupxx.pgmon.service.impl;

import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.PokemonDetailsDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.PokemonDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.dto.PokemonTypeDto;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.PokemonType;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Species;
import at.ac.fhstp.awp_bad.groupxx.pgmon.persistence.SpeciesRepository;
import at.ac.fhstp.awp_bad.groupxx.pgmon.service.SpeciesService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesRepository;

    public SpeciesServiceImpl(SpeciesRepository speciesRepository){
        this.speciesRepository = speciesRepository;
    }

    @Override
    public List<PokemonDto> getAllPokemon(String language) throws Exception {
        Iterable<Species> species = speciesRepository.findAll();
        List<PokemonDto> pokemonDtos = new ArrayList<>();

        for(Species speciesSingle : species){
            PokemonDto pokemonDto = new PokemonDto();

            if(language.equals("de")){
                pokemonDto.setName(speciesSingle.getName_de());
            } else if(language.equals("en")) {
                pokemonDto.setName(speciesSingle.getName_en());
            } else {
                throw new Exception();
            }
            pokemonDto.setSpeciesId(speciesSingle.getSpeciesId());

            pokemonDtos.add(pokemonDto);
        }

        return pokemonDtos;
    }

    @Override
    public PokemonDetailsDto getPokemonBySpeciesId(Integer id, String language){
        Species species = speciesRepository.findBySpeciesId(id);

        PokemonDetailsDto pokemonDetailsDto = new PokemonDetailsDto();
        pokemonDetailsDto.setHeight(species.getHeight());
        pokemonDetailsDto.setWidth(species.getWeight());
        pokemonDetailsDto.setName(language.equals("de") ? species.getName_de() : species.getName_en());

        List<PokemonTypeDto> pokemonTypeDtos = new ArrayList<>();

        for(PokemonType pokemonType : species.getPokemonTypes()){
            PokemonTypeDto pokemonTypeDto = new PokemonTypeDto();
            pokemonTypeDto.setName(language.equals("de") ? pokemonType.getTypeName_de() : pokemonType.getTypeName_en());
            pokemonTypeDtos.add(pokemonTypeDto);
        }

        pokemonDetailsDto.setTypes(pokemonTypeDtos);

        return pokemonDetailsDto;
    }
}

package at.ac.fhstp.awp_bad.groupxx.pgmon.dto;

import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.PokemonType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonDetailsDto {
    String name;

    @JsonProperty("pokedex-id")
    int speciesId;

    List<PokemonTypeDto> types;

    int width;

    int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public List<PokemonTypeDto> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonTypeDto> types) {
        this.types = types;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

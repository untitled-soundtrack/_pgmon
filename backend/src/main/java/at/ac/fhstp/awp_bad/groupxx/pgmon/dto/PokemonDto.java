package at.ac.fhstp.awp_bad.groupxx.pgmon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonDto {
    String name;

    @JsonProperty("pokedexId")
    int speciesId;

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
}

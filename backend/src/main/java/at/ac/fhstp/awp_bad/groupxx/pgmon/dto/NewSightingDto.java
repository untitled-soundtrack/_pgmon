package at.ac.fhstp.awp_bad.groupxx.pgmon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class NewSightingDto {

    @JsonProperty("pokedex-id")
    private Integer pokedexId;

    private PositionDto position;


    public Integer getPokedexId() {
        return pokedexId;
    }

    public void setPokedexId(Integer pokedexId) {
        this.pokedexId = pokedexId;
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }
}
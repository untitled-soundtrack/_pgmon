package at.ac.fhstp.awp_bad.groupxx.pgmon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class SightingDto {
    @JsonProperty("sighting-id")
    private Integer sightingId;
    @JsonProperty("pokedex-id")
    private Integer pokedexId;
    private Instant timestamp;

    private PositionDto position = new PositionDto();

    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getSightingId() {
        return sightingId;
    }

    public void setSightingId(Integer sightingId) {
        this.sightingId = sightingId;
    }

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


package at.ac.fhstp.awp_bad.groupxx.pgmon.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Sighting {
    @Id
    @GeneratedValue
    private Integer sightingId;
    private Instant timestamp;
    private Integer pokedexId;
    private Double lat;
    private Double lng;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant instant) {
        this.timestamp = instant;
    }

    public Integer getPokedexId() {
        return pokedexId;
    }

    public void setPokedexId(Integer pokedexId) {
        this.pokedexId = pokedexId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getSightingId() {
        return sightingId;
    }

    public void setSightingId(Integer sightingId) {
        this.sightingId = sightingId;
    }
}



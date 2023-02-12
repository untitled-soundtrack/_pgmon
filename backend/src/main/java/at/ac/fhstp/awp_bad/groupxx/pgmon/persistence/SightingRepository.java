package at.ac.fhstp.awp_bad.groupxx.pgmon.persistence;

import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Sighting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SightingRepository extends CrudRepository<Sighting, Integer> {

    @Query(value = "SELECT s from Sighting s WHERE s.lat >= :north AND s.lat <= :south AND s.lng >= :west AND s.lng <= :east")
    List<Sighting> getSightingsByGeo(@Param("north") Double north, @Param("east") Double east, @Param("south") Double south, @Param("west") Double west);
}
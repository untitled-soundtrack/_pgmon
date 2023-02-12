package at.ac.fhstp.awp_bad.groupxx.pgmon.persistence;

import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Species;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends CrudRepository<Species,Integer> {
    Species findBySpeciesId(Integer id);
}

package at.ac.fhstp.awp_bad.groupxx.pgmon.persistence;

import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Integer>{
}

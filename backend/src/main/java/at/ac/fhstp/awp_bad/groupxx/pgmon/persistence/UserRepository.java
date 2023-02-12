package at.ac.fhstp.awp_bad.groupxx.pgmon.persistence;

import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmailIgnoreCase(String email);
}

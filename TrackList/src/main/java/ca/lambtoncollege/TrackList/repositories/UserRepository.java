package ca.lambtoncollege.TrackList.repositories;

import ca.lambtoncollege.TrackList.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Case-insensitive methods to filter users by name
    List<User> findByNameIgnoreCase(String name);

    // Case-sensitive methods to filter by email and age
    List<User> findByEmail(String email);

    List<User> findByAge(Integer age); // Age filtering remains case-sensitive

    // Case-insensitive methods to filter by combinations of two attributes
    List<User> findByNameIgnoreCaseAndEmail(String name, String email);

    List<User> findByNameIgnoreCaseAndAge(String name, Integer age);

    List<User> findByEmailAndAge(String email, Integer age);

    // Case-insensitive method to filter by all three attributes
    List<User> findByNameIgnoreCaseAndEmailAndAge(String name, String email, Integer age);

    // Method to check if a user with a given email already exists
    boolean existsByEmail(String email);
}

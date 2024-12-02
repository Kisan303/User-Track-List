package ca.lambtoncollege.TrackList;

import ca.lambtoncollege.TrackList.models.User;
import ca.lambtoncollege.TrackList.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
        // Setting up test users
        user1 = new User();
        user1.setName("John doe");
        user1.setEmail("john.doe@example.com");
        user1.setAge(30);

        user2 = new User();
        user2.setName("john Doe");
        user2.setEmail("jane.doe@example.com");
        user2.setAge(25);

        user3 = new User();
        user3.setName("john smith");
        user3.setEmail("john.smith@example.com");
        user3.setAge(30);

        // Saving users to the repository
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Test
    public void testFindByNameIgnoreCase() {
        // Test the case-insensitive find by name
        List<User> users = userRepository.findByNameIgnoreCase("John Doe");
        assertEquals(2, users.size(), "Expected two users with the name 'John Doe' ignoring case");
    }

    @Test
    public void testFindByEmail() {
        // Test the find by email
        List<User> users = userRepository.findByEmail("john.doe@example.com");
        assertEquals(1, users.size(), "Expected one user with the email 'john.doe@example.com'");
        assertEquals(user1, users.get(0), "The user returned should be the one with the matching email");
    }

    @Test
    public void testFindByAge() {
        // Test the find by age
        List<User> users = userRepository.findByAge(30);
        assertEquals(2, users.size(), "Expected two users with the age of 30");
    }

    @Test
    public void testFindByNameIgnoreCaseAndEmail() {
        // Test the find by name (case-insensitive) and email
        List<User> users = userRepository.findByNameIgnoreCaseAndEmail("john doe", "john.doe@example.com");
        assertEquals(1, users.size(), "Expected one user with the name 'John Doe' and email 'john.doe@example.com'");
        assertEquals(user1, users.get(0), "The user returned should match the criteria");
    }

    @Test
    public void testFindByNameIgnoreCaseAndAge() {
        // Test the find by name (case-insensitive) and age
        List<User> users = userRepository.findByNameIgnoreCaseAndAge("john doe", 30);
        assertEquals(1, users.size(), "Expected one user with the name 'John Doe' and age 30");
        assertEquals(user1, users.get(0), "The user returned should match the criteria");
    }

    @Test
    public void testExistsByEmail() {
        // Test the existsByEmail method
        assertTrue(userRepository.existsByEmail("john.doe@example.com"), "User with email 'john.doe@example.com' should exist");
        assertFalse(userRepository.existsByEmail("non.existing@example.com"), "User with email 'non.existing@example.com' should not exist");
    }

    // Add more test cases as needed for other methods
}

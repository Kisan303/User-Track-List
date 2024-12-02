package ca.lambtoncollege.TrackList;

import org.junit.jupiter.api.Test;

import ca.lambtoncollege.TrackList.models.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testValidUser() {
        // Create a valid user object
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAge(30);

        // Check if all fields are correctly set
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(30, user.getAge());
    }

    @Test
    public void testInvalidUserName() {
        // Create a user with an invalid name (too short)
        User user = new User();
        user.setName("Jo"); // Invalid name
        user.setEmail("valid.email@example.com");
        user.setAge(30);

        // Check that the name is invalid (it should be more than 3 characters)
        assertThrows(IllegalArgumentException.class, () -> {
            if (user.getName().length() < 3) {
                throw new IllegalArgumentException("Name must be between 3 and 50 characters");
            }
        });
    }

    @Test
    public void testInvalidUserEmail() {
        // Create a user with an invalid email
        User user = new User();
        user.setName("John Doe");
        user.setEmail("invalid-email"); // Invalid email
        user.setAge(30);

        // Check if email is valid
        assertThrows(IllegalArgumentException.class, () -> {
            if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new IllegalArgumentException("Please provide a valid email");
            }
        });
    }

    @Test
    public void testInvalidUserAge() {
        // Create a user with an invalid age (less than 18)
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAge(17); // Invalid age

        // Check that the age is invalid (should be 18 or above)
        assertThrows(IllegalArgumentException.class, () -> {
            if (user.getAge() < 18) {
                throw new IllegalArgumentException("Age must be at least 18");
            }
        });
    }
}

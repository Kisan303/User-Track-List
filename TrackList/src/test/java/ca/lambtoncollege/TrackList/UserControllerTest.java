package ca.lambtoncollege.TrackList;

import ca.lambtoncollege.TrackList.controllers.UserController;
import ca.lambtoncollege.TrackList.models.User;
import ca.lambtoncollege.TrackList.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Mock
    private Model model;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setEmail("test@example.com");
        user.setName("Test User");
        user.setAge(30);
    }

    // Test to show the form
    @Test
    public void testShowForm() throws Exception {
        mockMvc.perform(get("/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attributeExists("user"));
    }

    // Test form submission with valid user
    @Test
    public void testSubmitForm_validUser() throws Exception {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);

        mockMvc.perform(post("/form")
                        .param("name", user.getName())
                        .param("email", user.getEmail())
                        .param("age", String.valueOf(user.getAge())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/list"));

        verify(userRepository, times(1)).save(user);
    }

    // Test form submission with an existing email (invalid)
    @Test
    public void testSubmitForm_duplicateEmail() throws Exception {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        mockMvc.perform(post("/form")
                        .param("name", user.getName())
                        .param("email", user.getEmail())
                        .param("age", String.valueOf(user.getAge())))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attributeHasFieldErrors("user", "email"));

        verify(userRepository, never()).save(user);
    }

    // Test listing users without filters
    @Test
    public void testListUsers_noFilters() throws Exception {
        when(userRepository.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", List.of(user)));
    }

    // Test listing users with name filter
    @Test
    public void testListUsers_withNameFilter() throws Exception {
        when(userRepository.findByNameIgnoreCase("Test User")).thenReturn(List.of(user));

        mockMvc.perform(get("/list").param("name", "Test User"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", List.of(user)));
    }

    // Test listing users with no results found
    @Test
    public void testListUsers_noResultsFound() throws Exception {
        when(userRepository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "No users found matching the given criteria."));
    }
}

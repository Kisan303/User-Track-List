package ca.lambtoncollege.TrackList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import ca.lambtoncollege.TrackList.controllers.PageHitController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(PageHitController.class)
public class PageHitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Reset pageHits before each test to ensure tests are independent
    @BeforeEach
    public void setup() {
        // Reset static variable pageHits to 0 before each test
        // This can be done manually or you can use reflection if needed.
        try {
            java.lang.reflect.Field field = PageHitController.class.getDeclaredField("pageHits");
            field.setAccessible(true);
            field.set(null, 0L); // Resetting the static field
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPageHits_initialValue() throws Exception {
        mockMvc.perform(get("/api/hits"))
               .andExpect(status().isOk())
               .andExpect(content().string("1"));
    }

    @Test
    public void testGetPageHits_afterMultipleCalls() throws Exception {
        mockMvc.perform(get("/api/hits"))
               .andExpect(status().isOk())
               .andExpect(content().string("1"));

        mockMvc.perform(get("/api/hits"))
               .andExpect(status().isOk())
               .andExpect(content().string("2"));
    }
}

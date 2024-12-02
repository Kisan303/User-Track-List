package ca.lambtoncollege.TrackList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.lambtoncollege.TrackList.services.PageHitService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageHitServiceTest {

    private PageHitService pageHitService;

    @BeforeEach
    public void setUp() {
        // Initialize the service before each test
        pageHitService = new PageHitService();
    }

    @Test
    public void testIncrementAndGetHits() {
        // Test if hits are incremented correctly
        assertEquals(1, pageHitService.incrementAndGetHits(), "Hits should be 1 after first increment");
        assertEquals(2, pageHitService.incrementAndGetHits(), "Hits should be 2 after second increment");
        assertEquals(3, pageHitService.incrementAndGetHits(), "Hits should be 3 after third increment");
    }
}

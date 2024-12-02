package ca.lambtoncollege.TrackList.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageHitController {
    private static long pageHits = 0;

    @GetMapping("/api/hits")
    public String getPageHits() {
        pageHits++;
        return String.valueOf(pageHits);
    }
}

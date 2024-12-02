package ca.lambtoncollege.TrackList.services;

import org.springframework.stereotype.Service;

@Service
public class PageHitService {
    private int hits = 0;

    public synchronized int incrementAndGetHits() {
        return ++hits;
    }
}
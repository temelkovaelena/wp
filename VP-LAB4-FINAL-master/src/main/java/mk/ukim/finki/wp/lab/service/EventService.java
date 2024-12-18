package mk.ukim.finki.wp.lab.service;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.SavedBooking;

import java.util.List;
import java.util.Optional;


public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text,double popularity);
    void  deleteById(Long id);
    Optional<Event> findById(Long id);
    Optional<Event> save(Long id, String name, String description, Double popularityScore, Location location);
     List<Event> findAllByLocationId(Long locationId);
    }
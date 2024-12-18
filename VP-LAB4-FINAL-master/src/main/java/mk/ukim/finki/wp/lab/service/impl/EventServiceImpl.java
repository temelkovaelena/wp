package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text,double popularity) {
        return eventRepository.findAll().stream()
                .filter(event -> (text==null || event.getName().toLowerCase().contains(text.toLowerCase())||
                         event.getDescription().toLowerCase().contains(text.toLowerCase()))
                && event.getPopularityScore() >= popularity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public Optional<Event> save(Long id, String name, String description, Double popularityScore, Location location) {
        Event event;
        if (id != null) {
            event = eventRepository.findById(id).orElse(new Event());
        } else {
            event = new Event();
        }
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(location);

        return Optional.of(eventRepository.save(event));
    }
    @Override
    public List<Event> findAllByLocationId(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }

}

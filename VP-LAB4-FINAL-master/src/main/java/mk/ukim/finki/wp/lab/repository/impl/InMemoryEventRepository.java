package mk.ukim.finki.wp.lab.repository.impl;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.SavedBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventRepository {
    private final List<SavedBooking> savedBookings = new ArrayList<>();

    public List<Event> findAll() {
        return DataHolder.eventList;
    }


    public boolean bookTickets(String eventName, int ticketsToBook) throws IllegalArgumentException {
        Optional<Event> event = DataHolder.eventList.stream()
                .filter(e -> e.getName().equals(eventName))
                .findFirst();

        if (event.isPresent()) {
            Event currentEvent = event.get();
            if (ticketsToBook <= 0) {
                throw new IllegalArgumentException("Number of tickets must be positive.");
            }
            if (ticketsToBook > currentEvent.getTicketCount()) {
                throw new IllegalArgumentException("The limit of tickets has been reached");
            }
            currentEvent.setTicketCount(currentEvent.getTicketCount() - ticketsToBook);
            return true;
        } else {
            throw new IllegalArgumentException("Event not found.");
        }
    }

    public List<Event> searchEvents(String text, double popularity) {
        return DataHolder.eventList.stream()
                .filter(event -> (text == null || text.isEmpty() || event.getName().contains(text)
                        || event.getDescription().contains(text)) && event.getPopularityScore() >= popularity)
                .collect(Collectors.toList());
    }

    public void addBooking(String eventName, String attendeeName, int tickets) {
        bookTickets(eventName, tickets);
        boolean bookingExists = false;

        for (SavedBooking booking : savedBookings) {
            if (booking.getEventName().equals(eventName) && booking.getAttendeeName().equals(attendeeName)) {
                booking.setNumberOfTickets(booking.getNumberOfTickets() + tickets);
                bookingExists = true;
                break;
            }
        }

        if (!bookingExists) {
            savedBookings.add(new SavedBooking(eventName, attendeeName, tickets));
        }
    }

    public void deleteById(Long id){
        DataHolder.eventList.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Event> findById(Long id){
        return DataHolder.eventList.stream()
                .filter(i->i.getId().equals(id))
                .findFirst();
    }



    public Optional<Event> save(String name, String description, Double popularityScore, Location location){
        if(location==null){
            throw new IllegalArgumentException();
        }
        Event event = new Event(name, description, popularityScore, location);
        DataHolder.eventList.removeIf(i-> i.getName().equals(event.getName()));
        DataHolder.eventList.add(event);
        return Optional.of(event);
    }


    public List<Event> findByName(String name) {
        return DataHolder.eventList.stream()
                .filter(event -> event.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Event> findByMinRating(Double rating) {
        return DataHolder.eventList.stream()
                .filter(event -> event.getPopularityScore() >= rating)
                .collect(Collectors.toList());
    }

    public List<Event> findByNameAndMinRating(String name, Double rating) {
        return DataHolder.eventList.stream()
                .filter(event -> event.getName().equalsIgnoreCase(name) && event.getPopularityScore() >= rating)
                .collect(Collectors.toList());
    }


//    public void addCommentToEvent(Long eventId, String username, String commentContent) {
//        Optional<Event> eventOptional = findById(eventId);
//        if (eventOptional.isPresent()) {
//            Event event = eventOptional.get();
//
//            Comment newComment = new Comment(username, commentContent);
//            event.getComments().add(String.valueOf(newComment));
//
//            save(event.getName(), event.getDescription(), event.getPopularityScore(), event.getLocation());
//        } else {
//            throw new RuntimeException("Event not found");
//        }
//    }


}
package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryEventBookingRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryEventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.EventBookingRepository;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventRepository eventRepository;
    private final EventBookingRepository bookingRepository;

    public EventBookingServiceImpl(EventRepository eventRepository, EventBookingRepository bookingRepository) {
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
    }


    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return new EventBooking(eventName,attendeeName,attendeeAddress, (long) numberOfTickets);
    }

    @Override
    public List<EventBooking> filterBookings(String name) {
        return bookingRepository.findAll().stream()
                .filter(booking -> booking.getAttendeeName().equals(name))
                .toList();
    }

}
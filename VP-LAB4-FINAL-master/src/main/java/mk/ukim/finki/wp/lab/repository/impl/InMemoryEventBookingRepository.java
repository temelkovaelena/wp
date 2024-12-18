package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryEventBookingRepository {
    public List<EventBooking> bookings=new ArrayList<>();
    public List<EventBooking> findAll(){
        return bookings;
    }

}
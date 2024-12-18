package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public  static List<Event> eventList = new ArrayList<>();
    public  static List<Location> locations = new ArrayList<>();

//    @PostConstruct
//    public void init(){
//        locations.add(new Location("Sunset Plaza", "102 Sunset Blvd, Rivertown", "350", "A vibrant open-air plaza with beautiful sunset views, perfect for gatherings and events."));
//        locations.add(new Location("Lakeside Pavilion", "204 Lake Shore Dr, Clearwater", "450", "A scenic lakeside venue offering spacious indoor and outdoor areas for weddings and corporate events."));
//        locations.add(new Location("Forest Retreat", "405 Pine Tree Ln, Greenwoods", "120", "A peaceful retreat surrounded by nature, ideal for meditation, yoga, and relaxation events."));
//        locations.add(new Location("Oceanview Theater", "55 Ocean Blvd, Seaside City", "700", "A modern theater with excellent acoustics and panoramic ocean views, hosting live performances and shows."));
//        locations.add(new Location("City Conference Hall", "123 Business Rd, Metropolis", "1000", "A large conference hall with state-of-the-art technology, accommodating seminars, expos, and conferences."));
//
//
//        eventList.add(new Event("Startup Pitch Night", "Presentations by innovative startups to investors", 18.00, 10, locations.get(1)));
//        eventList.add(new Event("Cooking Workshop", "Hands-on cooking class with a professional chef", 11.30, 7, locations.get(2)));
//        eventList.add(new Event("Photography Walk", "Guided tour for photographers around scenic spots", 9.00, 6, locations.get(3)));
//        eventList.add(new Event("Astronomy Night", "Stargazing event with telescopes and experts", 20.00, 9, locations.get(4)));
//        eventList.add(new Event("Yoga in the Park", "Outdoor yoga session for all skill levels", 7.30, 15, locations.get(0)));
//        eventList.add(new Event("Local Theater Play", "Live performance by the community theater group", 19.00, 20, locations.get(1)));
//        eventList.add(new Event("Wine Tasting", "Sampling of wines from local vineyards", 16.00, 7, locations.get(2)));
//        eventList.add(new Event("Meditation Retreat", "Day-long retreat focused on mindfulness and relaxation", 8.00, 6, locations.get(1)));
//        eventList.add(new Event("Coding Hackathon", "24-hour coding event for tech enthusiasts", 9.00, 7, locations.get(4)));
//        eventList.add(new Event("Charity Art Auction", "Auction of art pieces to raise funds for charity", 17.00, 8, locations.get(1)));
//
//
//    }
}

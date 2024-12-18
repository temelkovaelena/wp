package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String capacity;
    private String description;
    @OneToMany(mappedBy = "location")
    private List<Event> events;

    public Location(String name, String address, String capacity, String description) {

        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
    }

    public Location() {
        this.events = new ArrayList<>();
    }
}


/*
INSERT INTO LOCATION (ADDRESS, CAPACITY, DESCRIPTION, NAME) VALUES
('123 Main St, Ohrid, Macedonia', 50, 'A cozy venue near the lake', 'Lakeview Hall'),
('456 City Center Blvd, Skopje, Macedonia', 200, 'Spacious conference hall in the city center', 'City Conference Center'),
('789 Sunset Ave, Bitola, Macedonia', 75, 'Charming event space with a rustic vibe', 'Sunset Pavilion'),
('321 Riverbank Rd, Struga, Macedonia', 100, 'Modern venue with riverside views', 'Riverside Venue'),
('654 Historic Ln, Ohrid, Macedonia', 30, 'Intimate space with historic charm', 'Historic Hall');

 */
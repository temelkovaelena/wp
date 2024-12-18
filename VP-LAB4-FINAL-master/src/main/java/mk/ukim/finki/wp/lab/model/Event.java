package mk.ukim.finki.wp.lab.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
public class Event {
   private  String name;
   private String description;
   private double popularityScore;
   private int ticketCount;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @ManyToOne
   private Location location;
  // private List<String> comments;


   public Event(String name, String description, double popularityScore, int ticketCount, Location location) {
      this.name = name;
      this.description = description;
      this.popularityScore = popularityScore;
      this.ticketCount = ticketCount;
      Random random = new Random();
      this.id = random.nextLong();
      this.location = location;
   }

   public Event(String name, String description, double popularityScore,  Location location) {
      this.name = name;
      this.description = description;
      this.popularityScore = popularityScore;
      this.location = location;
    //  this.comments = new ArrayList<>();

   }

   public Event() {

   }

}


/*
INSERT INTO EVENT (POPULARITY_SCORE, TICKET_COUNT, LOCATION_ID, DESCRIPTION, NAME) VALUES
(8.5, 100, 1, 'An evening of classical music by the lake', 'Lake Symphony'),
(7.8, 150, 2, 'Tech conference featuring top industry speakers', 'Tech Innovators Summit'),
(9.2, 80, 3, 'A delightful evening of jazz performances', 'Jazz Under the Stars'),
(8.0, 120, 4, 'Stand-up comedy show with renowned comedians', 'Laugh Out Loud Night'),
(9.5, 50, 5, 'Art exhibition showcasing local artists', 'Expressions in Art');

 */
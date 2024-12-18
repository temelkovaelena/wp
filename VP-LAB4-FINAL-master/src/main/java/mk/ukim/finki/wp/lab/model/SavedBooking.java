package mk.ukim.finki.wp.lab.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class SavedBooking {
    private String eventName;
    private String attendeeName;
    private int numberOfTickets;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public SavedBooking(String eventName, String attendeeName, int numberOfTickets) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.numberOfTickets = numberOfTickets;
    }

    public SavedBooking() {

    }
}
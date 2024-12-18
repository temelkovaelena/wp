package mk.ukim.finki.wp.lab.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
public class EventBooking {
    private String eventName;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberOfTickets;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public EventBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
    }

    public EventBooking() {

    }
}

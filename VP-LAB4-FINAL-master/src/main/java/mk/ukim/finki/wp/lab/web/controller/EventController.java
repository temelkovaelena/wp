package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Comment;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String searchName,
                                @RequestParam(required = false) String minRating,
                                @RequestParam(required = false) String error,
                                Model model, HttpServletRequest req, HttpSession session) {

        boolean isAuthenticated = session.getAttribute("user") != null;

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Event> events;
        List<Location> locations = locationService.findAll();

        if ((searchName == null || searchName.isEmpty()) && (minRating == null || minRating.isEmpty())) {
            events = eventService.listAll();
        } else {
            double minRatingP = minRating != null && !minRating.isEmpty() ? Double.parseDouble(minRating) : 0.0;
            events = eventService.searchEvents(searchName, minRatingP);
        }

        model.addAttribute("events", events);
        model.addAttribute("clientIpAddress", req.getRemoteAddr());
        model.addAttribute("locations", locations);
        model.addAttribute("isAuthenticated", isAuthenticated);  // Додадено во моделот

        return "listEvents";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Location> locations = locationService.findAll();
            model.addAttribute("locations", locations);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-event")
    public String addEvent(Model model) {
        List<Event> events = this.eventService.listAll();
        List<Location> locations = locationService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("locations", locations);

        return "add-event";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String saveEvent(@RequestParam(required = false) Long id,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long locationId) {
        Location location = locationService.findById(locationId).orElse(null);
        if (location == null) {
            return "redirect:/events?error=InvalidLocation";
        }
        this.eventService.save(id, name, description, popularityScore, location);
        return "redirect:/events";
    }


    @GetMapping("/details/{id}")
    public String showEventDetails(@PathVariable Long id, Model model) {
        Optional<Event> optionalEvent = eventService.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            model.addAttribute("event", event);
            return "eventDetails";
        } else {
            return "redirect:/events?error=Event not found";
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        if (eventService.findById(id).isPresent()) {
            this.eventService.deleteById(id);
            return "redirect:/events";
        } else return "redirect:/events?error=Event not found";
    }

    @GetMapping("/by-location")
    public String getEventsByLocation(@RequestParam Long locationId, Model model) {
        List<Event> events = eventService.findAllByLocationId(locationId);
        model.addAttribute("events", events);
        model.addAttribute("locations", locationService.findAll());
        return "listEvents";
    }
}

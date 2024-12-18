package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.SavedBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@WebServlet(name = "event-booking", urlPatterns = "/eventBoking")
public class EventBookingServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final EventBookingService bookingService;
    private final EventService eventService;

    public EventBookingServlet(SpringTemplateEngine springTemplateEngine, EventBookingService bookingService, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookingService = bookingService;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext context = new WebContext(webExchange, req.getLocale());
        String eventName = req.getParameter("eventName");
        String attendeeName = req.getParameter("nameAttendee");
        String attendeeAddress = req.getRemoteAddr();
        int numberOfTickets = Integer.parseInt(req.getParameter("numTickets"));

        EventBooking booking = bookingService.placeBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
        context.setVariable("booking", booking);


        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }

}
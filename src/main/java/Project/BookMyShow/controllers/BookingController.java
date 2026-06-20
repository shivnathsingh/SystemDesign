package Project.BookMyShow.controllers;


import Project.BookMyShow.dtos.CreateBookingRequestDto;
import Project.BookMyShow.dtos.CreateBookingResponseDto;
import Project.BookMyShow.models.Booking;
import Project.BookMyShow.models.BookingStatus;
import Project.BookMyShow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {


    BookingService bookingService;
    public BookingController(BookingService bookingService)
    {
        this.bookingService=new BookingService();
    }
    /*

    To call createBooking method of service layer we need object of that class
    so manually we can create but manual create break ioc and handled by spring now



     */
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto requestDto)
    {

        return null;
    }
}

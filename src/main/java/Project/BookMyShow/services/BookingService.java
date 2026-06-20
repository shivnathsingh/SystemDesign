package Project.BookMyShow.services;

import Project.BookMyShow.dtos.CreateBookingRequestDto;
import Project.BookMyShow.dtos.CreateBookingResponseDto;
import Project.BookMyShow.exceptions.SeatNotFoundException;
import Project.BookMyShow.exceptions.UserNotFoundException;
import Project.BookMyShow.models.*;
import Project.BookMyShow.repositories.BookingRepository;
import Project.BookMyShow.repositories.ShowRepository;
import Project.BookMyShow.repositories.ShowSeatRepository;
import Project.BookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service     // so that manual create of objects not required
public class BookingService {

    @Autowired
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    /*
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto)
    {
        return null;
    }
    1. here this will be tight coupling because createBooking method can we called from
       multiple places so it should not be return any specific type
    2. service layer should not talk to dto's it should work on Java objects

     */

    public  BookingService()
    {

    }

    public BookingService(UserRepository userRepository,ShowRepository showRepository,ShowSeatRepository showSeatRepository)
    {
        this.userRepository=userRepository;
        this.showRepository=showRepository;
        this.showSeatRepository=showSeatRepository;
    }

    @Transactional
    public Booking createBooking(Long userId, List<Long> showSeatId,Long showId) throws Exception
    {
        /*
        steps for 1st app. without double lock checking
        1. Get the user with given userId
        2. Get the show with given showId
        3. Get the list of showSeatsId's with the given showSeatId's
        --------------------- TAKE LOCK --------------------
        4. Check if all the seats available or not
        5. If not throw an exception
        6. If yes , Marks the status of all seats as BLOCKED
        -------------------- RELEASE LOCK -------------------
        7. Save the changes to DB as well
        8. Create the Booking object with pending status and save to DB
        9. Return the booking object

        ======== Payment will be handled by other service ===========

         */

        //1. Get the user with given userId
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty())
        {
            throw new UserNotFoundException("User this given Id not present");
        }

        //2. Get the show with given showId
        Optional<Show> optionalShow=showRepository.findById(showId);

        //3. Get the list of showSeatsId's with the given showSeatId's
        List<ShowSeat> showSeats=showSeatRepository.findByShowIdAndSeatIdIn(showId,showSeatId);

        // 4. Check if all the seats available or not

//        for (ShowSeat showSeat:showSeats) {
//            if (showSeat.getShowSeatStatus() != ShowSeatStatus.AVAILABLE) {
//            throw  new UserNotFoundException("Select seat not availve ");
//
//        }
//        }

        int rowUpdateCount=showSeatRepository.lockSeats(showId,ShowSeatStatus.LOCKED,showSeatId,ShowSeatStatus.AVAILABLE);

        if(rowUpdateCount!=showSeatId.size())
        {
         throw new SeatNotFoundException("All Selected seat not available");
        }

        //        5. If not throw an exception

        //        6. If yes , Marks the status of all seats as BLOCKED
//        for(ShowSeat showSeat:showSeats)
//        {
//            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
//        }
//        7. Save the changes to DB as well
        showSeatRepository.saveAll(showSeats);
//        8. Create the Booking object with pending status and save to DB
        Booking booking = new Booking();
        booking.setShow(optionalShow.get());
        booking.setBookingStatus(BookingStatus.PENDING);

//      9. Save booking object
        bookingRepository.save(booking);

//        10. Return the booking object
        return booking;
    }

}

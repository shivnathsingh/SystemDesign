package Project.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModle{


    private String bookingNumber;
    @ManyToOne
    private User user;
    @ManyToOne
    private Show show; // but not require because ShowSeat will have it
    @ManyToMany        // in one booking we can book multiple show_seat and if someone cancel bokked ticket same showseat will assign to other booking
    private List<ShowSeat> showSeats;
    private long amount;
    @OneToMany
    private List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    /*

    1           -->         1
    Booking ------------ User M:1
     M         <----      1

     1          -->         1
    Booking ------------ Show M:1
     M         <----      1


     */
}

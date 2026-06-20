package Project.BookMyShow.dtos;

import Project.BookMyShow.models.Show;
import Project.BookMyShow.models.ShowSeat;
import Project.BookMyShow.models.User;
import lombok.Data;

import java.util.List;

@Data
public class CreateBookingRequestDto {

    private User userID;
    private Show showId;   // optional
    private List<ShowSeat> showSeatId;
}

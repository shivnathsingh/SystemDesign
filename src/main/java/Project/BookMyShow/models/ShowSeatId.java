package Project.BookMyShow.models;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ShowSeatId extends BaseModle{

    @ManyToOne
    private Show showId;
    @ManyToOne
    private Seat seatId;
}

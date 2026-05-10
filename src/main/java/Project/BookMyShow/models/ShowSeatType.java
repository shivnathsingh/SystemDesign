package Project.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModle{

    @ManyToOne
    private Show show;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    private int price;

    /*

    ShowSeatType
    S1A1
    S1A2
    S2A1
    S2A3

    Show
    S1
    S2

    1                               1
    ShowSeatType    ------------- Show
    M                                1



     */
}

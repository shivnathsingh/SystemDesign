package Project.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseModle{

    private int rowNum;
    private int colNum;
    private String number;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}

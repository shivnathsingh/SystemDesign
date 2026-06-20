package Project.BookMyShow.dtos;

import lombok.Data;

@Data
public class CreateBookingResponseDto {
    private String bookingNumber;
    private ReponseStatus status;
}

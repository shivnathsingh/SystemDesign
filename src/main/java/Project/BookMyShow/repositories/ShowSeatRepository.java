package Project.BookMyShow.repositories;

import Project.BookMyShow.models.ShowSeat;
import Project.BookMyShow.models.ShowSeatId;
import Project.BookMyShow.models.ShowSeatStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findByShowIdAndSeatIdIn(
            Long showId,
            List<Long> seatIds
    );

    @Modifying
    @Transactional
    @Query("""
            update ShowSeat ss 
            set ss.status=:lockedStatus
            where ss.showId=:showId and 
            ss.seatId=:seatId and 
            ss.satus=:availableStatus
            
            """)
    int lockSeats(@Param("showId") Long showId,
                  @Param("lockedStatus")ShowSeatStatus lockedStatus,
                  @Param("seatId") List<Long> seatId,
                  @Param("availableStatus") ShowSeatStatus availableStatus);

}

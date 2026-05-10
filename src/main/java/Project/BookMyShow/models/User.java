package Project.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="users")            // bydefault create with class name but we can give
public class User extends BaseModle {

    // private long id; not required defined in BaseModle class
    //@ManyToOne
    private  String name;
    private String email;
    private String password;
    @OneToMany
    private List<Booking> bookings;
}

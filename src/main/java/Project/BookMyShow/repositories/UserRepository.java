package Project.BookMyShow.repositories;

import Project.BookMyShow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
public class UserRepository {



    how to store data in DB
    user_id(PK) -> value other column

     */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Override
    Optional<User> findById(Long userId);
}


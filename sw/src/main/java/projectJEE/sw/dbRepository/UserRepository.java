package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query(" select u from User u " +
            " where u.username = ?1")
    Optional<User> findUserWithName(String username);
    User findUserByUsername(String username);
}

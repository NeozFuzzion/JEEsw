package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.GvoTeam;
import projectJEE.sw.dbEntity.User;

import java.util.List;

@Repository
public interface GvoTeamRepository extends JpaRepository<GvoTeam, Long> {
    List<GvoTeam> findAllByUserEquals(User user);

    GvoTeam findFirstByIdTeamAndUser(long id, User user);


}

package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.Query;
import projectJEE.sw.dbEntity.GameMonster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Monster;

import java.util.List;

@Repository
public interface GameMonsterRepository extends JpaRepository<GameMonster,Long> {
    public GameMonster findFirstByIdMonster(long id);

    @Query("SELECT m from GameMonster m WHERE natural_stars = 5L AND obtainable = true ")
    public List<GameMonster> findAllNatural5Monsters();
    @Query("SELECT m from GameMonster m WHERE natural_stars = 4L AND obtainable = true ")
    public List<GameMonster> findAllNatural4Monsters();
    @Query("SELECT m from GameMonster m WHERE natural_stars = 3L AND obtainable = true ")
    public List<GameMonster> findAllNatural3Monsters();
}
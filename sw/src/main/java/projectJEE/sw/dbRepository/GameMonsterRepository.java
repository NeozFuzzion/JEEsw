package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.GameMonster;

import java.util.List;

@Repository
public interface GameMonsterRepository extends JpaRepository<GameMonster,Long> {
    GameMonster findFirstByIdMonster(long id);
    @Query("SELECT m from GameMonster m WHERE natural_stars = 5L AND obtainable = true" )
    List<GameMonster> findAllNatural5Monsters();
    @Query("SELECT m from GameMonster m WHERE natural_stars = 4L AND obtainable = true")
    List<GameMonster> findAllNatural4Monsters();
    @Query("SELECT m from GameMonster m WHERE awaken_lvl = 2 AND obtainable = true")
    List<GameMonster> findAllNatural2AMonsters();
    @Query("SELECT m from GameMonster m WHERE natural_stars = 3L AND obtainable = true")
    List<GameMonster> findAllNatural3Monsters();
    @Query("SELECT m from GameMonster m WHERE natural_stars = 2L AND obtainable = true")
    List<GameMonster> findAllNatural2Monsters();
    @Query("SELECT m from GameMonster m WHERE natural_stars = 1L AND obtainable = true")
    List<GameMonster> findAllNatural1Monsters();

    GameMonster findFirstByName(String monster);

    @Query("SELECT m from GameMonster m WHERE natural_stars > 2L AND obtainable = true")
    List<GameMonster> findAllMonsters();
}
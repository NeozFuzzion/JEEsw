package projectJEE.sw.dbRepository;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projectJEE.sw.dbEntity.GameMonster;
import projectJEE.sw.dbEntity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.User;
import projectJEE.sw.model.MonsterId;

import java.util.List;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, MonsterId> {
    Monster findFirstByIdMonster(long id);
    @Query("SELECT m from Monster m WHERE m.gameMonster.natural_stars = 5L and m.jSON=:jsonChosen and m.gameMonster.obtainable = true and m.idMonster.user=:user ORDER BY m.gameMonster.idMonster")
    List<Monster> findAllNatural5Monsters(@Param("user") User user,@Param("jsonChosen") String jsonChosen);
    @Query("SELECT m from Monster m WHERE m.gameMonster.natural_stars = 4L and m.gameMonster.obtainable = true and m.jSON=:jsonChosen and m.idMonster.user=:user ORDER BY m.gameMonster.idMonster")
    List<Monster> findAllNatural4Monsters(@Param("user") User user,@Param("jsonChosen") String jsonChosen);
    @Query("SELECT m from Monster m WHERE m.gameMonster.awaken_lvl = 2 and m.gameMonster.obtainable = true and m.jSON=:jsonChosen and m.idMonster.user=:user ORDER BY m.gameMonster.idMonster")
    List<Monster> findAllNatural2AMonsters(@Param("user") User user,@Param("jsonChosen") String jsonChosen);

    @Query("SELECT m from Monster m WHERE m.gameMonster.idMonster = :id and m.idMonster.user=:user ORDER BY m.gameMonster.idMonster")
    List<Monster> findAllMonstersUser(@Param("user") User user,@Param("id") Long idMonster);

    @Query("SELECT DISTINCT m.jSON from Monster m WHERE user_id=:user")
    List<String> findAllJson(@Param("user") User user);

}

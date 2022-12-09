package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.Query;
import projectJEE.sw.dbEntity.GameMonster;
import projectJEE.sw.dbEntity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterRepository extends JpaRepository<Monster,Long> {
    public Monster findFirstByIdMonster(long id);
    @Query("SELECT m from Monster m WHERE m.gameMonster.natural_stars = 5L")
    public List<Monster> findAllNatural5Monsters();
    @Query("SELECT m from Monster m WHERE m.gameMonster.natural_stars = 4L")
    public List<Monster> findAllNatural4Monsters();
    @Query("SELECT m from Monster m WHERE m.gameMonster.natural_stars = 3L")
    public List<Monster> findAllNatural3Monsters();
}

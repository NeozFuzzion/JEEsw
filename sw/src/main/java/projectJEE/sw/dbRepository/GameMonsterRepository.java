package projectJEE.sw.dbRepository;

import projectJEE.sw.dbEntity.GameMonster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameMonsterRepository extends JpaRepository<GameMonster,Long> {
    public GameMonster findFirstByIdMonster(long id);
}
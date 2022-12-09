package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.LeaderSkill;

@Repository
public interface LeaderSkillRepository extends JpaRepository<LeaderSkill,Long> {

}
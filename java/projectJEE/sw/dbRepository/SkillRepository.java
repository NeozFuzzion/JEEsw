package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
    Skill findFirstByGameSkill(long id);
}

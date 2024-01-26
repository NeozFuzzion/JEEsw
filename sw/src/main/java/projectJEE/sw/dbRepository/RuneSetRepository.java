package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.RuneSet;
@Repository
public interface RuneSetRepository extends JpaRepository<RuneSet, Integer> {

}

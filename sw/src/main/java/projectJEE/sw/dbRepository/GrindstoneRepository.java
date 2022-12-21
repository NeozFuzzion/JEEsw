package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Grindstone;
import projectJEE.sw.dbEntity.StatRune;

@Repository
public interface GrindstoneRepository extends JpaRepository<Grindstone, Integer> {

}

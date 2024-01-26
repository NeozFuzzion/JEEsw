package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Grindstone;

@Repository
public interface GrindstoneRepository extends JpaRepository<Grindstone, Integer> {

}

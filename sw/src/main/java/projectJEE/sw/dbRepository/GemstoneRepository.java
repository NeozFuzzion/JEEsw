package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Gemstone;

@Repository
public interface GemstoneRepository extends JpaRepository<Gemstone, Integer> {

}

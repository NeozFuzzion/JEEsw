package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Gemstone;
import projectJEE.sw.dbEntity.StatRune;

@Repository
public interface GemstoneRepository extends JpaRepository<Gemstone, Integer> {

}

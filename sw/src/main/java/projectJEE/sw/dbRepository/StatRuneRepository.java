package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.Query;
import projectJEE.sw.dbEntity.Rune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.StatRune;

import java.util.List;

@Repository
public interface StatRuneRepository extends JpaRepository<StatRune, Long> {

}

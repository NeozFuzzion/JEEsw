package projectJEE.sw.dbRepository;

import projectJEE.sw.dbEntity.Rune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuneRepository extends JpaRepository<Rune, Long> {
    public Rune findFirstByIdRune(long id);
}

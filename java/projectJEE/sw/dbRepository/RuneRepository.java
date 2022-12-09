package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.Query;
import projectJEE.sw.dbEntity.Rune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuneRepository extends JpaRepository<Rune, Long> {
    public Rune findFirstByIdRune(long id);

    public List<Rune> findAllByOrderByIdRuneDesc();
}

package projectJEE.sw.dbRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import projectJEE.sw.dbEntity.Rune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuneRepository extends JpaRepository<Rune, Long> {

    List<Rune> findAll(Sort sort);

    @Query("select r from Rune r where r.set_id=:set and r.classe > 6")
    List<Rune> findAllAncientBySet(@Param("set") int set, Sort sort);

    @Query("select r from Rune r where r.set_id=:set and r.classe < 7")
    List<Rune> findAllNonAncientBySet(@Param("set") int set,Sort sort);
    @Query("select r from Rune r where r.set_id=:set")
    List<Rune> findAllBySet(@Param("set") int set,Sort sort);
    @Query("select r from Rune r where r.classe > 6")
    List<Rune> findAllAncient(Sort sort);
    @Query("select r from Rune r where r.classe < 7")
    List<Rune> findAllNonAncient(Sort sort);




}

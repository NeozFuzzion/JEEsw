package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projectJEE.sw.dbEntity.Rune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.model.RuneId;

import java.util.List;

@Repository
public interface RuneRepository extends JpaRepository<Rune, RuneId> {
    Rune findFirstByIdRune(Long id);
    List<Rune> findAllByOrderByIdRuneDesc();
    List<Rune> findAllByOrderByEfficiencyDesc();
    @Query("select r from Rune r where r.set_id=:set and r.classe > 6 order by efficiency desc")
    List<Rune> findAllAncientBySet(@Param("set") int set);
    @Query("select r from Rune r where r.set_id=:set and r.classe < 7 order by efficiency desc")
    List<Rune> findAllNonAncientBySet(@Param("set") int set);
    @Query("select r from Rune r where r.set_id=:set order by efficiency desc")
    List<Rune> findAllBySet(@Param("set") int set);
    @Query("select r from Rune r where r.classe > 6 order by efficiency desc")
    List<Rune> findAllAncient();
    @Query("select r from Rune r where r.classe < 7 order by efficiency desc")
    List<Rune> findAllNonAncient();


}

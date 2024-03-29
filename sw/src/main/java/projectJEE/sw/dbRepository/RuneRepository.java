package projectJEE.sw.dbRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Rune;
import projectJEE.sw.dbEntity.RuneSet;
import projectJEE.sw.dbEntity.User;
import projectJEE.sw.model.RuneId;

import java.util.List;

@Repository
public interface RuneRepository extends JpaRepository<Rune, RuneId> {
    Rune findFirstByIdRune(RuneId id);
    @Query("select r from Rune r where r.jSON=:jsonChosen and r.idRune.user=:user")
    List<Rune> findAll(Sort sort,@Param("jsonChosen") String jsonChosen,@Param("user") User user);

    @Query("select r from Rune r where r.jSON=:jsonChosen and r.idRune.user=:user and r.set_id=:set and r.classe > 6")
    List<Rune> findAllAncientBySet(@Param("set") RuneSet set, Sort sort,@Param("jsonChosen") String jsonChosen,@Param("user") User user);

    @Query("select r from Rune r where r.jSON=:jsonChosen and r.idRune.user=:user and r.set_id=:set and r.classe < 7 ")
    List<Rune> findAllNonAncientBySet(@Param("set") RuneSet set,Sort sort,@Param("jsonChosen") String jsonChosen,@Param("user") User user);
    @Query("select r from Rune r where r.jSON=:jsonChosen and r.idRune.user=:user and r.set_id=:set")
    List<Rune> findAllBySet(@Param("set") RuneSet set,Sort sort,@Param("jsonChosen") String jsonChosen,@Param("user") User user);
    @Query("select r from Rune r where r.jSON=:jsonChosen and  r.idRune.user=:user and r.classe > 6")
    List<Rune> findAllAncient(Sort sort,@Param("jsonChosen") String jsonChosen,@Param("user") User user);
    @Query("select r from Rune r where r.jSON=:jsonChosen and r.idRune.user=:user and r.classe < 7")
    List<Rune> findAllNonAncient(Sort sort,@Param("jsonChosen") String jsonChosen,@Param("user") User user);

    @Query("SELECT DISTINCT r.jSON from Rune r WHERE user_id=:user")
    List<String> findAllJson(@Param("user") User user);




}

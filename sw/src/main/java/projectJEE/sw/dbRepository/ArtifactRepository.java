package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Artifact;
import projectJEE.sw.dbEntity.User;
import projectJEE.sw.model.ArtifactId;

import java.util.List;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, ArtifactId> {

    Artifact findFirstByIdArtifact(ArtifactId id);
    @Query("select a from Artifact a where a.jSON=:jsonChosen and user_id=:user order by efficiency desc")
    List<Artifact> findAllByOrderByEfficiencyDesc(@Param("jsonChosen") String jsonChosen, @Param("user") User user);

    @Query("select a from Artifact a where a.jSON=:jsonChosen and a.restriction=:restriction and user_id=:user order by efficiency desc")
    List<Artifact> findAllByRestriction(@Param("restriction") String restriction,@Param("jsonChosen") String jsonChosen,@Param("user") User user);
    @Query("select a from Artifact a where a.jSON=:jsonChosen and a.type=:type and user_id=:user order by efficiency desc")
    List<Artifact> findAllByType(@Param("type") String type,@Param("jsonChosen") String jsonChosen,@Param("user") User user);

    @Query("SELECT DISTINCT a.jSON from Artifact a WHERE user_id=:user")
    List<String> findAllJson(@Param("user") User user);
}

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

    List<Artifact> findAllByOrderByEfficiencyDesc();

    @Query("select a from Artifact a where a.restriction=:restriction order by efficiency desc")
    List<Artifact> findAllByRestriction(@Param("restriction") String restriction);
    @Query("select a from Artifact a where a.type=:type order by efficiency desc")
    List<Artifact> findAllByType(@Param("type") String type);

    @Query("SELECT DISTINCT a.jSON from Artifact a WHERE user_id=:user")
    List<String> findAllJson(@Param("user") User user);
}

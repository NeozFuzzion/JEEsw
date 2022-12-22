package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.StatArtifact;

@Repository
public interface StatArtifactRepository extends JpaRepository<StatArtifact, Long> {
}

package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.sw.dbEntity.Artifact;

public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    public Artifact findFirstByIdArtifact(long id);
}

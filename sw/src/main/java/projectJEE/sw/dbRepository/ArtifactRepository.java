package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Artifact;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    public Artifact findFirstByIdArtifact(long id);
}

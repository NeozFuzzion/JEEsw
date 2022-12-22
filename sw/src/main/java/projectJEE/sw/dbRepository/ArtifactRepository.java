package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Artifact;
import projectJEE.sw.model.ArtifactId;

import java.util.List;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, ArtifactId> {
    public Artifact findFirstByIdArtifact(long id);
    public List<Artifact> findAllByOrderByIdArtifactDesc();
}

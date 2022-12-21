package projectJEE.sw.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import projectJEE.sw.dbEntity.Artifact;

import java.util.List;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    Artifact findFirstByIdArtifact(long id);
    List<Artifact> findAllByOrderByIdArtifactDesc();
}

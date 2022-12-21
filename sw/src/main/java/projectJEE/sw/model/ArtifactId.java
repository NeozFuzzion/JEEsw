package projectJEE.sw.model;

import projectJEE.sw.dbEntity.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

public class ArtifactId  implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long idArtifact;

    // default constructor

    public ArtifactId(User user, Long idRune) {
        this.idArtifact = idRune;
        this.user = user;
    }

    public ArtifactId() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getIdRune() {
        return idArtifact;
    }

    public void setIdRune(long idRune) {
        this.idArtifact = idRune;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtifactId that)) return false;
        return Objects.equals(user, that.user) && Objects.equals(idArtifact, that.idArtifact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, idArtifact);
    }
}
package projectJEE.sw.model;

import projectJEE.sw.dbEntity.User;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RuneId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long idRune;

    // default constructor

    public RuneId(User user, Long idRune) {
        this.idRune = idRune;
        this.user = user;
    }

    public RuneId() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getIdRune() {
        return idRune;
    }

    public void setIdRune(long idRune) {
        this.idRune = idRune;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RuneId runeId)) return false;
        return idRune == runeId.idRune && user.equals(runeId.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, idRune);
    }
}
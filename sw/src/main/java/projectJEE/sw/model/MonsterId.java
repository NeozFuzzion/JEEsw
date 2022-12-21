package projectJEE.sw.model;

import projectJEE.sw.dbEntity.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MonsterId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    private Long idMonster;

    // default constructor

    public MonsterId(User user, Long idMonster) {
        this.idMonster = idMonster;
        this.user = user;
    }

    public MonsterId() {

    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getIdMonster() {
        return idMonster;
    }

    public void setIdMonster(long idMonster) {
        this.idMonster = idMonster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonsterId monsterId)) return false;
        return idMonster == monsterId.idMonster && Objects.equals(user, monsterId.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, idMonster);
    }
}
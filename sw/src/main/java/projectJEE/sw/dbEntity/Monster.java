package projectJEE.sw.dbEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Monster {
    @Id    @Column
    private long idMonster;

    @Column
    private String name;

    public long getIdMonster() {
        return idMonster;
    }

    public void setIdMonster(long idMonster) {
        this.idMonster = idMonster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "idMonster=" + idMonster +
                ", name='" + name + '\'' +
                '}';
    }
}

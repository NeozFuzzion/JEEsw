package projectJEE.sw.dbEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Monster {
    @Id    @Column
    private long idMonster;

    @Id
    private long id_game;

    @Column
    private String skills;

    @Column
    private String rune1;

    @Column
    private String rune2;

    @Column
    private String rune3;

    @Column
    private String rune4;

    @Column
    private String rune5;

    @Column
    private String rune6;

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

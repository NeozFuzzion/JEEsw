package projectJEE.sw.dbEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Monster {
    @Id    @Column
    private long idMonster;

    @Column
    private long id_game;

    @Column
    private String skillsup;

    @Column
    private long rune1;

    @Column
    private long rune2;

    @Column
    private long rune3;

    @Column
    private long rune4;

    @Column
    private long rune5;

    @Column
    private long rune6;

    @Column
    private long unit_level;

    public long getIdMonster() {
        return idMonster;
    }

    public void setIdMonster(long idMonster) {
        this.idMonster = idMonster;
    }

    public long getId_game() {
        return id_game;
    }

    public void setId_game(long id_game) {
        this.id_game = id_game;
    }

    public String getSkillsup() {
        return skillsup;
    }

    public void setSkillsup(String skillsup) {
        this.skillsup = skillsup;
    }

    public long getUnit_level() {
        return unit_level;
    }

    public void setUnit_level(long unit_level) {
        this.unit_level = unit_level;
    }

    public long getRune1() {
        return rune1;
    }

    public void setRune1(long rune1) {
        this.rune1 = rune1;
    }

    public long getRune2() {
        return rune2;
    }

    public void setRune2(long rune2) {
        this.rune2 = rune2;
    }

    public long getRune3() {
        return rune3;
    }

    public void setRune3(long rune3) {
        this.rune3 = rune3;
    }

    public long getRune4() {
        return rune4;
    }

    public void setRune4(long rune4) {
        this.rune4 = rune4;
    }

    public long getRune5() {
        return rune5;
    }

    public void setRune5(long rune5) {
        this.rune5 = rune5;
    }

    public long getRune6() {
        return rune6;
    }

    public void setRune6(long rune6) {
        this.rune6 = rune6;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "idMonster=" + idMonster +
                ", id_game=" + id_game +
                ", skillsup='" + skillsup + '\'' +
                ", rune1='" + rune1 + '\'' +
                ", rune2='" + rune2 + '\'' +
                ", rune3='" + rune3 + '\'' +
                ", rune4='" + rune4 + '\'' +
                ", rune5='" + rune5 + '\'' +
                ", rune6='" + rune6 + '\'' +
                ", unit_level=" + unit_level +
                '}';
    }
}

package projectJEE.sw.dbEntity;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import projectJEE.sw.model.MonsterId;

import javax.persistence.*;

@Entity
public class Monster {
    @EmbeddedId
    private MonsterId idMonster;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_game", nullable = false)
    private GameMonster gameMonster;

    @Column
    private String skills;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas(value = {
            @JoinColumnOrFormula(formula = @JoinFormula(value = "user_id", referencedColumnName = "user_id")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "rune1", referencedColumnName = "idRune"))})
    private Rune rune1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas(value = {
            @JoinColumnOrFormula(formula = @JoinFormula(value = "user_id", referencedColumnName = "user_id")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "rune2", referencedColumnName = "idRune"))})
    private Rune rune2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas(value = {
            @JoinColumnOrFormula(formula = @JoinFormula(value = "user_id", referencedColumnName = "user_id")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "rune3", referencedColumnName = "idRune"))})
    private Rune rune3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas(value = {
            @JoinColumnOrFormula(formula = @JoinFormula(value = "user_id", referencedColumnName = "user_id")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "rune4", referencedColumnName = "idRune"))})
    private Rune rune4;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas(value = {
            @JoinColumnOrFormula(formula = @JoinFormula(value = "user_id", referencedColumnName = "user_id")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "rune5", referencedColumnName = "idRune"))})
    private Rune rune5;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas(value = {
            @JoinColumnOrFormula(formula = @JoinFormula(value = "user_id", referencedColumnName = "user_id")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "rune6", referencedColumnName = "idRune"))})
    private Rune rune6;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas(value = {
            @JoinColumnOrFormula(formula = @JoinFormula(value = "user_id", referencedColumnName = "user_id")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "artifact1", referencedColumnName = "idArtifact"))})
    private Artifact artifact1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas(value = {
            @JoinColumnOrFormula(formula = @JoinFormula(value = "user_id", referencedColumnName = "user_id")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "artifact2", referencedColumnName = "idArtifact"))})
    private Artifact artifact2;

    @Column
    private long unit_level;


    public GameMonster getGameMonster() {
        return gameMonster;
    }

    public String getSkills() {
        return skills;
    }

    public long getUnit_level() {
        return unit_level;
    }

    public Rune getRune1() {
        return rune1;
    }

    public Rune getRune2() {
        return rune2;
    }

    public Rune getRune3() {
        return rune3;
    }

    public Rune getRune4() {
        return rune4;
    }

    public Rune getRune5() {
        return rune5;
    }

    public Rune getRune6() {
        return rune6;
    }

    public Artifact getArtifact1() {
        return artifact1;
    }

    public Artifact getArtifact2() {
        return artifact2;
    }

    public MonsterId getIdMonster() {
        return idMonster;
    }


    public void setIdMonster(MonsterId idMonster) {
        this.idMonster = idMonster;
    }

    public void setGameMonster(GameMonster gameMonster) {
        this.gameMonster = gameMonster;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setRune1(Rune rune1) {
        this.rune1 = rune1;
    }

    public void setRune2(Rune rune2) {
        this.rune2 = rune2;
    }

    public void setRune3(Rune rune3) {
        this.rune3 = rune3;
    }

    public void setRune4(Rune rune4) {
        this.rune4 = rune4;
    }

    public void setRune5(Rune rune5) {
        this.rune5 = rune5;
    }

    public void setRune6(Rune rune6) {
        this.rune6 = rune6;
    }

    public void setUnit_level(long unit_level) {
        this.unit_level = unit_level;
    }

    public void setArtifact1(Artifact artifact1) {
        this.artifact1 = artifact1;
    }

    public void setArtifact2(Artifact artifact2) {
        this.artifact2 = artifact2;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "idMonster=" + idMonster +
                ", id_game=" + gameMonster +
                ", skills='" + skills + '\'' +
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
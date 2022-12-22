package projectJEE.sw.dbEntity;


import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import projectJEE.sw.model.RuneId;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Rune {
    @EmbeddedId
    private RuneId idRune;

    @Column
    private Long occupied_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas(value = {
            @JoinColumnOrFormula(formula = @JoinFormula(value = "user_id", referencedColumnName = "user_id")),
            @JoinColumnOrFormula(column = @JoinColumn(name = "occupied_id", referencedColumnName = "idMonster"))})
    private Monster occupied_id;

    @Column
    private int slot_no;

    @Column
    private int rang;

    @Column
    private int classe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "set_id", nullable = false)
    private RuneSet set_id;

    @Column
    private int upgrade_curr;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "statPri", nullable = false)
    private StatRune statPri;

    @Column
    private Long pri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statInnate")
    private StatRune statInnate;

    @Column
    private Long innate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subStat1")
    private StatRune subStat1;

    @Column
    private Long subStat1Value;

    @Column
    private Long subStat1Meule;

    @Column
    private Long subStat1Gemme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subStat2")
    private StatRune subStat2;

    @Column
    private Long subStat2Value;

    @Column
    private Long subStat2Meule;

    @Column
    private Long subStat2Gemme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subStat3")
    private StatRune subStat3;

    @Column
    private Long subStat3Value;

    @Column
    private Long subStat3Meule;

    @Column
    private Long subStat3Gemme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subStat4")
    private StatRune subStat4;

    @Column
    private Long subStat4Value;

    @Column
    private Long subStat4Meule;

    @Column
    private Long subStat4Gemme;

    @Column
    private float efficiency;

    @Column
    private float effMaxHero;

    @Column
    private float effMaxLegend;


    public RuneId getIdRune() {
        return idRune;
    }
    public void setIdRune(RuneId idRune) {
        this.idRune = idRune;
    }

    public Long getOccupied_type() {
        return occupied_type;
    }

    public void setOccupied_type(Long occupied_type) {
        this.occupied_type = occupied_type;
    }

    public Monster getOccupied_id() {
        return occupied_id;
    }

    public void setOccupied_id(Monster occupied_id) {
        this.occupied_id = occupied_id;
    }

    public int getSlot_no() {
        return slot_no;
    }

    public void setSlot_no(int slot_no) {
        this.slot_no = slot_no;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public RuneSet getSet_id() {
        return set_id;
    }

    public void setSet_id(RuneSet set_id) {
        this.set_id = set_id;
    }

    public int getUpgrade_curr() {
        return upgrade_curr;
    }

    public void setUpgrade_curr(int upgrade_curr) {
        this.upgrade_curr = upgrade_curr;
    }

    public StatRune getStatPri() {
        return statPri;
    }

    public void setStatPri(StatRune statPri) {
        this.statPri = statPri;
    }

    public Long getPri() {
        return pri;
    }

    public void setPri(Long pri) {
        this.pri = pri;
    }

    public StatRune getStatInnate() {
        return statInnate;
    }

    public void setStatInnate(StatRune statInnate) {
        this.statInnate = statInnate;
    }

    public Long getInnate() {
        return innate;
    }

    public void setInnate(Long innate) {
        this.innate = innate;
    }

    public StatRune getSubStat1() {
        return subStat1;
    }

    public void setSubStat1(StatRune subStat1) {
        this.subStat1 = subStat1;
    }

    public Long getSubStat1Value() {
        return subStat1Value;
    }

    public void setSubStat1Value(Long subStat1Value) {
        this.subStat1Value = subStat1Value;
    }

    public Long getSubStat1Meule() {
        return subStat1Meule;
    }

    public void setSubStat1Meule(Long subStat1Meule) {
        this.subStat1Meule = subStat1Meule;
    }

    public Long getSubStat1Gemme() {
        return subStat1Gemme;
    }

    public void setSubStat1Gemme(Long subStat1Gemme) {
        this.subStat1Gemme = subStat1Gemme;
    }

    public StatRune getSubStat2() {
        return subStat2;
    }

    public void setSubStat2(StatRune subStat2) {
        this.subStat2 = subStat2;
    }

    public Long getSubStat2Value() {
        return subStat2Value;
    }

    public void setSubStat2Value(Long subStat2Value) {
        this.subStat2Value = subStat2Value;
    }

    public Long getSubStat2Meule() {
        return subStat2Meule;
    }

    public void setSubStat2Meule(Long subStat2Meule) {
        this.subStat2Meule = subStat2Meule;
    }

    public Long getSubStat2Gemme() {
        return subStat2Gemme;
    }

    public void setSubStat2Gemme(Long subStat2Gemme) {
        this.subStat2Gemme = subStat2Gemme;
    }

    public StatRune getSubStat3() {
        return subStat3;
    }

    public void setSubStat3(StatRune subStat3) {
        this.subStat3 = subStat3;
    }

    public Long getSubStat3Value() {
        return subStat3Value;
    }

    public void setSubStat3Value(Long subStat3Value) {
        this.subStat3Value = subStat3Value;
    }

    public Long getSubStat3Meule() {
        return subStat3Meule;
    }

    public void setSubStat3Meule(Long subStat3Meule) {
        this.subStat3Meule = subStat3Meule;
    }

    public Long getSubStat3Gemme() {
        return subStat3Gemme;
    }

    public void setSubStat3Gemme(Long subStat3Gemme) {
        this.subStat3Gemme = subStat3Gemme;
    }

    public StatRune getSubStat4() {
        return subStat4;
    }

    public void setSubStat4(StatRune subStat4) {
        this.subStat4 = subStat4;
    }

    public Long getSubStat4Value() {
        return subStat4Value;
    }

    public void setSubStat4Value(Long subStat4Value) {
        this.subStat4Value = subStat4Value;
    }

    public Long getSubStat4Meule() {
        return subStat4Meule;
    }

    public void setSubStat4Meule(Long subStat4Meule) {
        this.subStat4Meule = subStat4Meule;
    }

    public Long getSubStat4Gemme() {
        return subStat4Gemme;
    }

    public void setSubStat4Gemme(Long subStat4Gemme) {
        this.subStat4Gemme = subStat4Gemme;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency;
    }

    public float getEffMaxHero() {
        return effMaxHero;
    }

    public void setEffMaxHero(float effMaxHero) {
        this.effMaxHero = effMaxHero;
    }

    public float getEffMaxLegend() {
        return effMaxLegend;
    }

    public void setEffMaxLegend(float effMaxLegend) {
        this.effMaxLegend = effMaxLegend;
    }

    @Override
    public String toString() {
        return
                ", slot_no=" + slot_no +
                ", rang=" + rang +
                ", classe=" + classe +
                ", set_id=" + set_id +
                ", upgrade_curr=" + upgrade_curr +
                ", statPri=" + statPri +
                ", pri=" + pri +
                ", statInnate=" + statInnate +
                ", innate=" + innate +
                ", subStat1=" + subStat1 +
                ", subStat1Value=" + subStat1Value +
                ", subStat1Meule=" + subStat1Meule +
                ", subStat1Gemme=" + subStat1Gemme +
                ", subStat2=" + subStat2 +
                ", subStat2Value=" + subStat2Value +
                ", subStat2Meule=" + subStat2Meule +
                ", subStat2Gemme=" + subStat2Gemme +
                ", subStat3=" + subStat3 +
                ", subStat3Value=" + subStat3Value +
                ", subStat3Meule=" + subStat3Meule +
                ", subStat3Gemme=" + subStat3Gemme +
                ", subStat4=" + subStat4 +
                ", subStat4Value=" + subStat4Value +
                ", subStat4Meule=" + subStat4Meule +
                ", subStat4Gemme=" + subStat4Gemme +
                ", efficiency=" + efficiency +
                '}';
    }
}

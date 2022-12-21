package projectJEE.sw.dbEntity;

import javax.persistence.*;

@Entity
public class GameMonster {

    @Id @Column
    private long idMonster;

    @Column
    private String name;

    @Column
    private boolean obtainable;
    @Column
    private long hp;

    @Column
    private long def;

    @Column
    private long atk;

    @Column
    private long spd;

    @Column
    private long crate;

    @Column
    private long cdmg;

    @Column
    private long res;

    @Column
    private long acc;

    @Column
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_skill")
    private LeaderSkill leader_skill;

    @Column
    private String element;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s1")
    private Skill S1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s2")
    private Skill S2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s3")
    private Skill S3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s4")
    private Skill S4;

    @Column
    private long awaken_lvl;

    @Column
    private long awakens_from;

    @Column
    private boolean can_awaken;

    @Column
    private  String awaken_bonus;

    @Column
    private long family_id;

    @Column
    private long natural_stars;

    @Column
    private long skill_ups_to_max;

    @Column
    private String archetype;

    @Column
    private boolean fusion_food;

    public long getIdMonster() {
        return idMonster;
    }

    public String getName() {
        return name;
    }

    public long getHp() {
        return hp;
    }

    public long getDef() {
        return def;
    }

    public long getAtk() {
        return atk;
    }

    public long getSpd() {
        return spd;
    }

    public long getCrate() {
        return crate;
    }

    public long getCdmg() {
        return cdmg;
    }

    public long getRes() {
        return res;
    }

    public long getAcc() {
        return acc;
    }

    public String getImage() {
        return image;
    }

    public LeaderSkill getLeader_skill() {
        return leader_skill;
    }

    public String getElement() {
        return element;
    }

    public Skill getS1() {
        return S1;
    }

    public Skill getS2() {
        return S2;
    }

    public Skill getS3() {
        return S3;
    }

    public Skill getS4() {
        return S4;
    }

    public long getAwaken_lvl() {
        return awaken_lvl;
    }

    public long getAwakens_from() {
        return awakens_from;
    }

    public boolean isCan_awaken() {
        return can_awaken;
    }

    public String getAwaken_bonus() {
        return awaken_bonus;
    }

    public long getFamily_id() {
        return family_id;
    }

    public long getNatural_stars() {
        return natural_stars;
    }

    public long getSkill_ups_to_max() {
        return skill_ups_to_max;
    }

    public String getArchetype() {
        return archetype;
    }

    public boolean isObtainable() {
        return obtainable;
    }

    public void setObtainable(boolean obtainable) {
        this.obtainable = obtainable;
    }

    public boolean isFusion_food() {
        return fusion_food;
    }

    public void setIdMonster(long idMonster) {
        this.idMonster = idMonster;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public void setDef(long def) {
        this.def = def;
    }

    public void setAtk(long atk) {
        this.atk = atk;
    }

    public void setSpd(long spd) {
        this.spd = spd;
    }

    public void setCrate(long crate) {
        this.crate = crate;
    }

    public void setCdmg(long cdmg) {
        this.cdmg = cdmg;
    }

    public void setRes(long res) {
        this.res = res;
    }

    public void setAcc(long acc) {
        this.acc = acc;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLeader_skill(LeaderSkill leader_skill) {
        this.leader_skill = leader_skill;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setS1(Skill s1) {
        S1 = s1;
    }

    public void setS2(Skill s2) {
        S2 = s2;
    }

    public void setS3(Skill s3) {
        S3 = s3;
    }

    public void setS4(Skill s4) {
        S4 = s4;
    }

    public void setAwaken_lvl(long awaken_lvl) {
        this.awaken_lvl = awaken_lvl;
    }

    public void setAwakens_from(long awakens_from) {
        this.awakens_from = awakens_from;
    }

    public void setCan_awaken(boolean can_awaken) {
        this.can_awaken = can_awaken;
    }

    public void setAwaken_bonus(String awaken_bonus) {
        this.awaken_bonus = awaken_bonus;
    }

    public void setFamily_id(long family_id) {
        this.family_id = family_id;
    }

    public void setNatural_stars(long natural_stars) {
        this.natural_stars = natural_stars;
    }

    public void setSkill_ups_to_max(long skill_ups_to_max) {
        this.skill_ups_to_max = skill_ups_to_max;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public void setFusion_food(boolean fusion_food) {
        this.fusion_food = fusion_food;
    }

    @Override
    public String toString() {
        return "GameMonster{" +
                "idMonster=" + idMonster +
                ", hp=" + hp +
                ", def=" + def +
                ", atk=" + atk +
                ", spd=" + spd +
                ", crate=" + crate +
                ", cdmg=" + cdmg +
                ", res=" + res +
                ", acc=" + acc +
                ", image='" + image + '\'' +
                ", leader_skill=" + leader_skill +
                ", element='" + element + '\'' +
                ", S1=" + S1 +
                ", S2=" + S2 +
                ", S3=" + S3 +
                ", S4=" + S4 +
                ", awaken_lvl=" + awaken_lvl +
                ", awakens_from=" + awakens_from +
                ", can_awaken=" + can_awaken +
                ", awaken_bonus='" + awaken_bonus + '\'' +
                ", family_id=" + family_id +
                ", natural_stars=" + natural_stars +
                ", skill_ups_to_max=" + skill_ups_to_max +
                ", archetype='" + archetype + '\'' +
                ", fusion_food=" + fusion_food +
                '}';
    }

}

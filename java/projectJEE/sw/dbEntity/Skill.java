package projectJEE.sw.dbEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Skill {

    @Id @Column
    private Long idSkill;

    @Column
    private Long gameSkill;

    @Column
    private String name;

    @Column(columnDefinition="LONGTEXT")
    private String description;

    @Column
    private String image;

    @Column
    private long cooltime;

    @Column
    private long hits;

    @Column
    private long slot;

    @Column
    private boolean passive;

    @Column
    private boolean aoe;

    @Column
    private long max_level;

    @Column(columnDefinition="LONGTEXT")
    private String upgrades;

    @Column(columnDefinition="LONGTEXT")
    private String effects;

    @Column
    private String multiplier_formula;

    public Long getIdSkill() {
        return idSkill;
    }

    public Long getGameSkill() {
        return gameSkill;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public long getCooltime() {
        return cooltime;
    }

    public long getHits() {
        return hits;
    }

    public long getSlot() {
        return slot;
    }

    public boolean isPassive() {
        return passive;
    }

    public boolean isAoe() {
        return aoe;
    }

    public long getMax_level() {
        return max_level;
    }

    public String getUpgrades() {
        return upgrades;
    }

    public String getEffects() {
        return effects;
    }

    public String getMultiplier_formula() {
        return multiplier_formula;
    }

    public void setIdSkill(Long idSkill) {
        this.idSkill = idSkill;
    }

    public void setGameSkill(Long gameSkill) {
        this.gameSkill = gameSkill;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCooltime(long cooltime) {
        this.cooltime = cooltime;
    }

    public void setHits(long hits) {
        this.hits = hits;
    }

    public void setSlot(long slot) {
        this.slot = slot;
    }

    public void setPassive(boolean passive) {
        this.passive = passive;
    }

    public void setAoe(boolean aoe) {
        this.aoe = aoe;
    }

    public void setMax_level(long max_level) {
        this.max_level = max_level;
    }

    public void setUpgrades(String upgrades) {
        this.upgrades = upgrades;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public void setMultiplier_formula(String multiplier_formula) {
        this.multiplier_formula = multiplier_formula;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "idSkill=" + idSkill +
                ", gameSkill=" + gameSkill +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", cooltime=" + cooltime +
                ", hits=" + hits +
                ", slot=" + slot +
                ", passive=" + passive +
                ", aoe=" + aoe +
                ", max_level=" + max_level +
                ", upgrades='" + upgrades + '\'' +
                ", effects='" + effects + '\'' +
                ", multiplier_formula='" + multiplier_formula + '\'' +
                '}';
    }
}

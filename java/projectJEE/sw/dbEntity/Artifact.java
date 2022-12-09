package projectJEE.sw.dbEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Artifact {

    @Id @Column
    private long idArtifact;

    @Column
    private long occupied_id;

    @Column
    private int slot;

    @Column
    private int type;

    @Column
    private int attribute;

    @Column
    private int unit_style;

    @Column
    private int natural_rank;

    @Column
    private int rang;

    @Column
    private int level;

    @Column
    private String pri_effect;

    @Column
    private String sec_effect;

    @Column
    private long efficiency;


    public long getIdArtifact() {
        return idArtifact;
    }

    public void setIdArtifact(long idArtifact) {
        this.idArtifact = idArtifact;
    }

    public long getOccupied_id() {
        return occupied_id;
    }

    public void setOccupied_id(long occupied_id) {
        this.occupied_id = occupied_id;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    public int getUnit_style() {
        return unit_style;
    }

    public void setUnit_style(int unit_style) {
        this.unit_style = unit_style;
    }

    public int getNatural_rank() {
        return natural_rank;
    }

    public void setNatural_rank(int natural_rank) {
        this.natural_rank = natural_rank;
    }
    public int getRang() {
        return rang;
    }

    public void setRang(int rank) {
        this.rang = rank;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPri_effect() {
        return pri_effect;
    }

    public void setPri_effect(String pri_effect) {
        this.pri_effect = pri_effect;
    }

    public String getSec_effect() {
        return sec_effect;
    }

    public void setSec_effect(String sec_effect) {
        this.sec_effect = sec_effect;
    }

    public long getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(long efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "idArtifact=" + idArtifact +
                ", occupied_id=" + occupied_id +
                ", slot=" + slot +
                ", type=" + type +
                ", attribute=" + attribute +
                ", unit_style=" + unit_style +
                ", natural_rank=" + natural_rank +
                ", rank=" + rang +
                ", level=" + level +
                ", pri_effect='" + pri_effect + '\'' +
                ", sec_effect='" + sec_effect + '\'' +
                '}';
    }
}

package projectJEE.sw.dbEntity;

import org.json.simple.JSONObject;
import projectJEE.sw.model.ArtifactId;

import javax.persistence.*;

@Entity
public class Artifact {

    @EmbeddedId
    private ArtifactId idArtifact;

    @Column
    private long occupied_id;

    @Column
    private int slot;

    @Column
    private String type;

    @Column
    private String restriction;

    @Column
    private int natural_rank;

    @Column
    private int rang;

    @Column
    private int level;

    @Column
    private String statPri;

    @Column
    private Long pri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subStat1")
    private StatArtifact subStat1;

    @Column
    private Float subStat1Value;

    @Column
    private Long subStat1Proc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subStat2")
    private StatArtifact subStat2;

    @Column
    private Float subStat2Value;

    @Column
    private Long subStat2Proc;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subStat3")
    private StatArtifact subStat3;

    @Column
    private Float subStat3Value;

    @Column
    private Long subStat3Proc;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subStat4")
    private StatArtifact subStat4;

    @Column
    private Float subStat4Value;

    @Column
    private Long subStat4Proc;

    @Column
    private Long subStatChange;

    @Column
    private float efficiency;

    @Column
    private String jSON;

    public String getjSON() {
        return jSON;
    }

    public void setjSON(String jSON) {
        this.jSON = jSON;
    }

    public ArtifactId getIdArtifact() {
        return idArtifact;
    }

    public void setIdArtifact(ArtifactId idArtifact) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String setRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
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

    public String getStatPri() {
        return statPri;
    }

    public void setStatPri(String statPri) {
        this.statPri = statPri;
    }

    public Long getPri() {
        return pri;
    }

    public void setPri(Long pri) {
        this.pri = pri;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency;
    }

    public StatArtifact getSubStat1() {
        return subStat1;
    }

    public void setSubStat1(StatArtifact subStat1) {
        this.subStat1 = subStat1;
    }

    public Float getSubStat1Value() {
        return subStat1Value;
    }

    public void setSubStat1Value(Float subStat1Value) {
        this.subStat1Value = subStat1Value;
    }

    public StatArtifact getSubStat2() {
        return subStat2;
    }

    public void setSubStat2(StatArtifact subStat2) {
        this.subStat2 = subStat2;
    }

    public Float getSubStat2Value() {
        return subStat2Value;
    }

    public void setSubStat2Value(Float subStat2Value) {
        this.subStat2Value = subStat2Value;
    }

    public StatArtifact getSubStat3() {
        return subStat3;
    }

    public void setSubStat3(StatArtifact subStat3) {
        this.subStat3 = subStat3;
    }

    public Float getSubStat3Value() {
        return subStat3Value;
    }

    public void setSubStat3Value(Float subStat3Value) {
        this.subStat3Value = subStat3Value;
    }

    public StatArtifact getSubStat4() {
        return subStat4;
    }

    public void setSubStat4(StatArtifact subStat4) {
        this.subStat4 = subStat4;
    }

    public Float getSubStat4Value() {
        return subStat4Value;
    }

    public void setSubStat4Value(Float subStat4Value) {
        this.subStat4Value = subStat4Value;
    }

    public Long getSubStatChange() {
        return subStatChange;
    }

    public void setSubStatChange(Long subStatChange) {
        this.subStatChange = subStatChange;
    }

    public Long getSubStat1Proc() {
        return subStat1Proc;
    }

    public void setSubStat1Proc(Long subStat1Proc) {
        this.subStat1Proc = subStat1Proc;
    }

    public Long getSubStat2Proc() {
        return subStat2Proc;
    }

    public void setSubStat2Proc(Long subStat2Proc) {
        this.subStat2Proc = subStat2Proc;
    }

    public Long getSubStat3Proc() {
        return subStat3Proc;
    }

    public void setSubStat3Proc(Long subStat3Proc) {
        this.subStat3Proc = subStat3Proc;
    }

    public Long getSubStat4Proc() {
        return subStat4Proc;
    }

    public void setSubStat4Proc(Long subStat4Proc) {
        this.subStat4Proc = subStat4Proc;
    }

    public String getRestriction() {
        return restriction;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "idArtifact=" + idArtifact +
                ", occupied_id=" + occupied_id +
                ", slot=" + slot +
                ", type=" + type +
                ", restriction=" + restriction +
                ", natural_rank=" + natural_rank +
                ", rang=" + rang +
                ", level=" + level +
                ", statPri='" + statPri + '\'' +
                ", pri=" + pri +
                ", subStat1=" + subStat1 +
                ", subStat1Value=" + subStat1Value +
                ", subStat1Proc=" + subStat1Proc +
                ", subStat2=" + subStat2 +
                ", subStat2Value=" + subStat2Value +
                ", subStat2Proc=" + subStat2Proc +
                ", subStat3=" + subStat3 +
                ", subStat3Value=" + subStat3Value +
                ", subStat3Proc=" + subStat3Proc +
                ", subStat4=" + subStat4 +
                ", subStat4Value=" + subStat4Value +
                ", subStat4Proc=" + subStat4Proc +
                ", subStatChange=" + subStatChange +
                ", efficiency=" + efficiency +
                '}';
    }
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("type",type);
        json.put("rang",rang);
        json.put("restriction",restriction);
        json.put("level",level);
        json.put("pri",pri);
        json.put("statPri",statPri);
        return json;
    }
}

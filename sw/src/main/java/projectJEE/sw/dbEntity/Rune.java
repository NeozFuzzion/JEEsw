package projectJEE.sw.dbEntity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rune {
    @Id @Column
    private long idRune;

    @Column
    private long occupied_type;

    @Column
    private long occupied_id;

    @Column
    private int slot_no;

    @Column
    private int rang;

    @Column
    private int classe;

    @Column
    private int set_id;

    @Column
    private int upgrade_curr;

    @Column
    private String pri_eff;

    @Column
    private String prefix_eff;

    @Column
    private String sec_eff;

    @Column
    private float efficiency;

    public long getIdRune() {
        return idRune;
    }

    public void setIdRune(long idRune) {
        this.idRune = idRune;
    }

    public long getOccupied_type() {
        return occupied_type;
    }

    public void setOccupied_type(long occupied_type) {
        this.occupied_type = occupied_type;
    }

    public long getOccupied_id() {
        return occupied_id;
    }

    public void setOccupied_id(long occupied_id) {
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

    public int getSet_id() {
        return set_id;
    }

    public void setSet_id(int set_id) {
        this.set_id = set_id;
    }

    public int getUpgrade_curr() {
        return upgrade_curr;
    }

    public void setUpgrade_curr(int upgrade_curr) {
        this.upgrade_curr = upgrade_curr;
    }

    public String getPri_eff() {
        return pri_eff;
    }

    public void setPri_eff(String pri_eff) {
        this.pri_eff = pri_eff;
    }

    public String getPrefix_eff() {
        return prefix_eff;
    }

    public void setPrefix_eff(String prefix_eff) {
        this.prefix_eff = prefix_eff;
    }

    public String getSec_eff() {
        return sec_eff;
    }

    public void setSec_eff(String sec_eff) {
        this.sec_eff = sec_eff;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return "Rune{" +
                "idRune=" + idRune +
                ", occupied_type=" + occupied_type +
                ", occupied_id=" + occupied_id +
                ", slot_no=" + slot_no +
                ", rang=" + rang +
                ", classe=" + classe +
                ", set_id=" + set_id +
                ", upgrade_curr=" + upgrade_curr +
                ", pri_eff='" + pri_eff + '\'' +
                ", prefix_eff='" + prefix_eff + '\'' +
                ", sec_eff='" + sec_eff + '\'' +
                ", efficiency=" + efficiency+
                '}';
    }
}

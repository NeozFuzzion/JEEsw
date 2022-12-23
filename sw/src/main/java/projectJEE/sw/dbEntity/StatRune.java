package projectJEE.sw.dbEntity;

import org.json.simple.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StatRune {

    @Id @Column
    private Long idStat;

    @Column
    private String name;

    @Column
    private Long maxMain6;

    @Column
    private Long maxMain5;

    @Column
    private Long maxMain4;

    @Column
    private Long maxMain3;

    @Column
    private Long maxMain2;

    @Column
    private Long maxMain1;

    @Column
    private Long maxSub6;

    @Column
    private Long maxSub5;

    @Column
    private Long maxSub4;

    @Column
    private Long maxSub3;

    @Column
    private Long maxSub2;

    @Column
    private Long maxSub1;

    public Long getIdStat() {
        return idStat;
    }

    public String getName() {
        return name;
    }

    public Long getMaxMain6() {
        return maxMain6;
    }

    public Long getMaxMain5() {
        return maxMain5;
    }

    public Long getMaxMain4() {
        return maxMain4;
    }

    public Long getMaxMain3() {
        return maxMain3;
    }

    public Long getMaxMain2() {
        return maxMain2;
    }

    public Long getMaxMain1() {
        return maxMain1;
    }

    public Long getMaxSub6() {
        return maxSub6;
    }

    public Long getMaxSub5() {
        return maxSub5;
    }

    public Long getMaxSub4() {
        return maxSub4;
    }

    public Long getMaxSub3() {
        return maxSub3;
    }

    public Long getMaxSub2() {
        return maxSub2;
    }

    public Long getMaxSub1() {
        return maxSub1;
    }

    public void setIdStat(Long idStat) {
        this.idStat = idStat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxMain6(Long maxMain6) {
        this.maxMain6 = maxMain6;
    }

    public void setMaxMain5(Long maxMain5) {
        this.maxMain5 = maxMain5;
    }

    public void setMaxMain4(Long maxMain4) {
        this.maxMain4 = maxMain4;
    }

    public void setMaxMain3(Long maxMain3) {
        this.maxMain3 = maxMain3;
    }

    public void setMaxMain2(Long maxMain2) {
        this.maxMain2 = maxMain2;
    }

    public void setMaxMain1(Long maxMain1) {
        this.maxMain1 = maxMain1;
    }

    public void setMaxSub6(Long maxSub6) {
        this.maxSub6 = maxSub6;
    }

    public void setMaxSub5(Long maxSub5) {
        this.maxSub5 = maxSub5;
    }

    public void setMaxSub4(Long maxSub4) {
        this.maxSub4 = maxSub4;
    }

    public void setMaxSub3(Long maxSub3) {
        this.maxSub3 = maxSub3;
    }

    public void setMaxSub2(Long maxSub2) {
        this.maxSub2 = maxSub2;
    }

    public void setMaxSub1(Long maxSub1) {
        this.maxSub1 = maxSub1;
    }

    @Override
    public String toString() {
        return "StatRune{" +
                "idStat=" + idStat +
                ", name='" + name + '\'' +
                ", maxMain6=" + maxMain6 +
                ", maxMain5=" + maxMain5 +
                ", maxMain4=" + maxMain4 +
                ", maxMain3=" + maxMain3 +
                ", maxMain2=" + maxMain2 +
                ", maxMain1=" + maxMain1 +
                ", maxSub6=" + maxSub6 +
                ", maxSub5=" + maxSub5 +
                ", maxSub4=" + maxSub4 +
                ", maxSub3=" + maxSub3 +
                ", maxSub2=" + maxSub2 +
                ", maxSub1=" + maxSub1 +
                '}';
    }
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("idStat",idStat);
        json.put("name",name);
        return json;
    }
}

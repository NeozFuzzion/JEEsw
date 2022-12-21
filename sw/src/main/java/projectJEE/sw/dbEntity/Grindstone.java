package projectJEE.sw.dbEntity;

import javax.persistence.*;

@Entity
public class Grindstone {
    @Id @Column
    private int idGrind;

    @Column
    private long minHero;

    @Column
    private long maxHero;

    @Column
    private long minLegend;

    @Column
    private long maxLegend;

    public int getIdGrind() {
        return idGrind;
    }

    public void setIdGrind(int idGrind) {
        this.idGrind = idGrind;
    }

    public long getMinHero() {
        return minHero;
    }

    public void setMinHero(long minHero) {
        this.minHero = minHero;
    }

    public long getMaxHero() {
        return maxHero;
    }

    public void setMaxHero(long maxHero) {
        this.maxHero = maxHero;
    }

    public long getMinLegend() {
        return minLegend;
    }

    public void setMinLegend(long minLegend) {
        this.minLegend = minLegend;
    }

    public long getMaxLegend() {
        return maxLegend;
    }

    public void setMaxLegend(long maxLegend) {
        this.maxLegend = maxLegend;
    }

}

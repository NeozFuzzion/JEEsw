package projectJEE.sw.dbEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Gemstone  {
    @Id @Column
    private int idGem;

    @Column
    private long minHero;

    @Column
    private long maxHero;

    @Column
    private long minLegend;

    @Column
    private long maxLegend;

    public int getIdGem() {
        return idGem;
    }

    public void setIdGem(int idGem) {
        this.idGem = idGem;
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

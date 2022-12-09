package projectJEE.sw.dbEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LeaderSkill {

    @Id @Column
    private Long idLs;

    @Column
    private Long amount;

    @Column
    private String area;

    @Column
    private String attribute;

    @Column
    private String element;

    @Column
    private String image;

    public Long getIdLs() {
        return idLs;
    }

    public Long getAmount() {
        return amount;
    }

    public String getArea() {
        return area;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getElement() {
        return element;
    }

    public String getImage() {
        return image;
    }

    public void setIdLs(Long idLs) {
        this.idLs = idLs;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "LeaderSkill{" +
                "idLs=" + idLs +
                ", amount=" + amount +
                ", area='" + area + '\'' +
                ", attribute='" + attribute + '\'' +
                ", element='" + element + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

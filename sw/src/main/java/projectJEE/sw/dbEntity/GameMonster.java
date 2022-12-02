package projectJEE.sw.dbEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GameMonster {

    @Id @Column
    private Long idMonster;

    public void setIdMonster(Long idMonster) {
        this.idMonster = idMonster;
    }

    @Id
    public Long getIdMonster() {
        return idMonster;
    }
}

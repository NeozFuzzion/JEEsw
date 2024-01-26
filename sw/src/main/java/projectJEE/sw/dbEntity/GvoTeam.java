package projectJEE.sw.dbEntity;

import javax.persistence.*;

@Entity
public class GvoTeam {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idTeam;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_monster1", nullable = false)
    private GameMonster monster1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_monster2")
    private GameMonster monster2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_monster3")
    private GameMonster monster3;

    public GameMonster getMonster1() {
        return monster1;
    }

    public GameMonster getMonster2() {
        return monster2;
    }

    public GameMonster getMonster3() {
        return monster3;
    }

    public void setMonster1(GameMonster monster1) {
        this.monster1 = monster1;
    }

    public void setMonster2(GameMonster monster2) {
        this.monster2 = monster2;
    }

    public void setMonster3(GameMonster monster3) {
        this.monster3 = monster3;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

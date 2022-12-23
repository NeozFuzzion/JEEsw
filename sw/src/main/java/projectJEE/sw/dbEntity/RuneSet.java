package projectJEE.sw.dbEntity;

import org.json.simple.JSONObject;

import javax.persistence.*;

@Entity
public class RuneSet {
    @Id @Column
    private int idSet;
    @Column
    private String name;
    @Column
    private String image;

    public int getIdSet() {
        return idSet;
    }

    public void setIdSet(int idSet) {
        this.idSet = idSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("idSet",idSet);
        json.put("name",name);
        json.put("image",image);
        return json;
    }
}

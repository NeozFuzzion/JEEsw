package projectJEE.sw.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projectJEE.sw.dbEntity.GameMonster;
import projectJEE.sw.dbEntity.Skill;
import projectJEE.sw.dbRepository.GameMonsterRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


@Controller
public class BestiaryController{
    @Autowired
    GameMonsterRepository gameMonsterRepository;
    @GetMapping(value = "/bestiary/{id}")
    public String bestiary(@PathVariable("id") long id, Model model) throws ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GameMonster monster =gameMonsterRepository.findFirstByIdMonster(id);
        Skill[] skills = {monster.getS1(),monster.getS2(),monster.getS3(),monster.getS4()};
        JSONParser jsonP = new JSONParser();
        for(int i=0;i< skills.length;i++){
            if(skills[i]!=null){
                Skill s = (Skill)GameMonster.class.getMethod("getS" + (i+1)).invoke(monster);
                JSONArray upgrades = (JSONArray)jsonP.parse(s.getUpgrades());
                String up ="";
                int j=1;
                for(JSONObject e : (Iterable<JSONObject>) upgrades){
                    String[] effect = e.get("effect").toString().split(" ");
                    String amount =e.get("amount").toString();
                    up+= "Lv." + j +" ";
                    if(effect[0].equals("Damage")||effect[0].equals("Recovery")){
                        up+=effect[0] +" +"+amount+"%";
                    }
                    if(effect[0].equals("Effect")){
                        up+=effect[0] +" rate +"+amount+"%";
                    }
                    if(effect[0].equals("Cooltime")){
                        up+=effect[0] +" -"+amount;
                    }
                    up+="\n";
                    j++;
                }
                model.addAttribute("ups"+ (i+1),up);
            }
        }

        model.addAttribute("monster",monster);

        return "/html/bestiary";
    }
}

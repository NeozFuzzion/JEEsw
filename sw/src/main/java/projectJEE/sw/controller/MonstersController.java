package projectJEE.sw.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import projectJEE.sw.dbEntity.Monster;
import projectJEE.sw.dbEntity.User;
import projectJEE.sw.dbRepository.GameMonsterRepository;
import projectJEE.sw.dbRepository.MonsterRepository;
import projectJEE.sw.dbRepository.UserRepository;

import java.util.List;

@Controller
public class MonstersController {
    @Autowired
    MonsterRepository monsterRepository;

    @Autowired
    GameMonsterRepository gameMonsterRepository ;

    @Autowired
    UserRepository userRepository ;

    @GetMapping("/monsters")
    public String monsters(Model model) {

        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<String> jsons=monsterRepository.findAllJson(user);
        if(!jsons.isEmpty()) {
            model.addAttribute("nat5", monsterRepository.findAllNatural5Monsters(user, jsons.get(0)));
            model.addAttribute("nat4", monsterRepository.findAllNatural4Monsters(user, jsons.get(0)));
            model.addAttribute("a2", monsterRepository.findAllNatural2AMonsters(user, jsons.get(0)));

            model.addAttribute("isConnected", !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"));
            model.addAttribute("jsons", jsons);
        }
        model.addAttribute("data",!jsons.isEmpty());
        return "/html/monsters";
    }
    @PostMapping("/monsters/filter")
    public ResponseEntity<JSONObject> runeFilter(@Param("jsonChosen") String jsonChosen){
        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Monster> nat5 = monsterRepository.findAllNatural5Monsters(user,jsonChosen);
        List<Monster> nat4 = monsterRepository.findAllNatural4Monsters(user,jsonChosen);
        List<Monster> a2 = monsterRepository.findAllNatural2AMonsters(user,jsonChosen);
        JSONObject filter = new JSONObject();
        filter.put("jsonToUse",jsonChosen);

        JSONArray nat5JSON = new JSONArray();
        nat5.forEach(monster -> {nat5JSON.add(monster.getGameMonster().toJSON());});
        filter.put("nat5",nat5JSON);

        JSONArray nat4JSON = new JSONArray();
        nat4.forEach(monster -> {nat4JSON.add(monster.getGameMonster().toJSON());});
        filter.put("nat4",nat4JSON);

        JSONArray a2JSON = new JSONArray();
        a2.forEach(monster -> {a2JSON.add(monster.getGameMonster().toJSON());});
        filter.put("a2",a2JSON);

        return ResponseEntity.status(HttpStatus.OK).body(filter);
    }


}

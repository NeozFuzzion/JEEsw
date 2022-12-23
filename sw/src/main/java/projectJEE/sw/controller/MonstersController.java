package projectJEE.sw.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import projectJEE.sw.dbEntity.GameMonster;
import projectJEE.sw.dbEntity.Monster;
import projectJEE.sw.dbEntity.User;
import projectJEE.sw.dbRepository.GameMonsterRepository;
import projectJEE.sw.dbRepository.MonsterRepository;
import projectJEE.sw.dbRepository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MonstersController {
    @Autowired
    MonsterRepository monsterRepository;

    @Autowired
    GameMonsterRepository gameMonsterRepository ;

    @Autowired
    UserRepository userRepository ;

    @GetMapping("/bestiary")
    public String gameMonsters(Model model) {

        model.addAttribute("nat5",gameMonsterRepository.findAllNatural5Monsters());
        model.addAttribute("nat4",gameMonsterRepository.findAllNatural4Monsters());
        model.addAttribute("nat3",gameMonsterRepository.findAllNatural3Monsters());
        model.addAttribute("a2",gameMonsterRepository.findAllNatural2AMonsters());
        model.addAttribute("nat2",gameMonsterRepository.findAllNatural2Monsters());
        model.addAttribute("nat1",gameMonsterRepository.findAllNatural1Monsters());

        model.addAttribute("isConnected", (SecurityContextHolder.getContext().getAuthentication().getName() != null));

        return "/html/abon";
    }

    @GetMapping("/monsters")
    public String monsters(Model model) {

        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<String> jsons=monsterRepository.findAllJson(user);
        model.addAttribute("nat5",monsterRepository.findAllNatural5Monsters(user, jsons.get(0)));
        model.addAttribute("nat4",monsterRepository.findAllNatural4Monsters(user, jsons.get(0)));
        model.addAttribute("a2",monsterRepository.findAllNatural2AMonsters(user, jsons.get(0)));

        model.addAttribute("isConnected", (SecurityContextHolder.getContext().getAuthentication().getName() != null));
        model.addAttribute("jsons",jsons);
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

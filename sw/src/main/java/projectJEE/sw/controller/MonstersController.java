package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projectJEE.sw.dbEntity.GameMonster;
import projectJEE.sw.dbEntity.Monster;
import projectJEE.sw.dbRepository.GameMonsterRepository;
import projectJEE.sw.dbRepository.MonsterRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MonstersController {
    @Autowired
    MonsterRepository monsterRepository;

    @Autowired
    GameMonsterRepository gameMonsterRepository ;

    @GetMapping("/bestiary")
    public String gameMonsters(Model model) {

        model.addAttribute("nat5",gameMonsterRepository.findAllNatural5Monsters());
        model.addAttribute("nat4",gameMonsterRepository.findAllNatural4Monsters());
        model.addAttribute("nat3",gameMonsterRepository.findAllNatural3Monsters());
        model.addAttribute("a2",gameMonsterRepository.findAllNatural2AMonsters());
        model.addAttribute("nat2",gameMonsterRepository.findAllNatural2Monsters());
        model.addAttribute("nat1",gameMonsterRepository.findAllNatural1Monsters());
        return "/html/abon";
    }

    @GetMapping("/monsters")
    public String monsters(Model model) {

        model.addAttribute("nat5",monsterRepository.findAllNatural5Monsters());
        model.addAttribute("nat4",monsterRepository.findAllNatural4Monsters());
        model.addAttribute("a2",monsterRepository.findAllNatural2AMonsters());

        return "/html/monsters";
    }


}

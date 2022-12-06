package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projectJEE.sw.dbEntity.GameMonster;
import projectJEE.sw.dbEntity.Monster;
import projectJEE.sw.dbRepository.MonsterRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MonstersController {
    @Autowired
    MonsterRepository monsterRepository;

    @GetMapping("/monsters")
    public String monsters(Model model) {

        model.addAttribute("nat5",monsterRepository.findAllNatural5Monsters());
        model.addAttribute("nat4",monsterRepository.findAllNatural4Monsters());
        model.addAttribute("nat3",monsterRepository.findAllNatural3Monsters());

        return "/html/monsters";
    }


}

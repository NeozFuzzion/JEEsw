package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projectJEE.sw.dbRepository.MonsterRepository;

import java.util.Collection;

@Controller
public class MonstersController {
    @Autowired
    MonsterRepository monsterRepository;

    @GetMapping("/monsters")
    public String monsters(Model model) {
        System.out.println( monsterRepository.findFirstByIdMonster(14738580379L).getGameMonster());
        return "/html/monsters";
    }
}

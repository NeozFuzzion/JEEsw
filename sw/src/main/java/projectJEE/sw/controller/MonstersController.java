package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import projectJEE.sw.dbRepository.MonsterRepository;

@Controller
public class MonstersController {
    @Autowired
    MonsterRepository monsterRepository;

    @GetMapping("/monsters")
    public String monsters() {

        System.out.println(monsterRepository.findFirstByIdMonster(14738580379L).getGameMonster());
        return "/html/monsters";
    }
}

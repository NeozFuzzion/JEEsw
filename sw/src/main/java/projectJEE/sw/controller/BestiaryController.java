package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projectJEE.sw.dbRepository.GameMonsterRepository;


@Controller
public class BestiaryController{
    @Autowired
    GameMonsterRepository gameMonsterRepository;
    @GetMapping(value = "/bestiary/{id}")
    public String bestiary(@PathVariable("id") long id, Model model){
        model.addAttribute("monster",gameMonsterRepository.findFirstByIdMonster(id));
        return "/html/bestiary";
    }
}

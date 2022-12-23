package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projectJEE.sw.dbEntity.GameMonster;
import projectJEE.sw.dbEntity.GvoTeam;
import projectJEE.sw.dbEntity.User;
import projectJEE.sw.dbRepository.GameMonsterRepository;
import projectJEE.sw.dbRepository.GvoTeamRepository;
import projectJEE.sw.dbRepository.UserRepository;
import projectJEE.sw.service.GvoTeamForm;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GvoTeamController {
    @Autowired
    GameMonsterRepository gameMonsterRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GvoTeamRepository gvoTeamRepository;

    @GetMapping("/gvoteam")
    public String gvoTeam(Model model){
        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<GvoTeam> gvoteams=gvoTeamRepository.findAllByUserEquals(user);
        model.addAttribute("gvoteams",gvoteams);
        return "/html/gvoteam";
    }

    @GetMapping("/gvoteam/update/{id}")
    public String gvoTeamUpdate(@PathVariable("id") long id,Model model){
        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        GvoTeamForm gvoTeamForm = new GvoTeamForm();
        GvoTeam gvoTeam = gvoTeamRepository.findFirstByIdTeamAndUser(id,user);
        if (gvoTeam !=null){
            gvoTeamForm.setMonster1(gvoTeam.getMonster1().getIdMonster());
            if (gvoTeam.getMonster2()!=null)
                gvoTeamForm.setMonster2(gvoTeam.getMonster2().getIdMonster());
            if (gvoTeam.getMonster3()!=null)
                gvoTeamForm.setMonster3(gvoTeam.getMonster3().getIdMonster());
            model.addAttribute("monster",gameMonsterRepository.findAllMonsters());
            model.addAttribute("gvoteamform",gvoTeamForm);
            model.addAttribute("idTeam",gvoTeam.getIdTeam());
            model.addAttribute("isConnected", (user != null));

            return "/html/teamBuilderUpdate";
        }

        return "redirect:/gvoteam";
    }

    @PostMapping("/gvoteam/update/{id}")
    public String gvoTeamUpdater(@ModelAttribute GvoTeamForm teamForm, @PathVariable("id") long id, Model model){
        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        GvoTeam gvoTeam = gvoTeamRepository.findFirstByIdTeamAndUser(id,user);
        if (teamForm.getMonster1()!=null){
            gvoTeam.setMonster1(gameMonsterRepository.getReferenceById(teamForm.getMonster1()));
            if (teamForm.getMonster2()!=null)
                gvoTeam.setMonster2(gameMonsterRepository.getReferenceById(teamForm.getMonster2()));
            if (teamForm.getMonster3()!=null)
                gvoTeam.setMonster3(gameMonsterRepository.getReferenceById(teamForm.getMonster3()));
            gvoTeamRepository.save(gvoTeam);
        }
        return "redirect:/gvoteam";
    }

    @GetMapping("/gvoteam/destroy/{id}")
    public String gvoTeamDestroyer(@PathVariable("id") long id){
        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<GvoTeam> gvoteams=gvoTeamRepository.findAllByUserEquals(user);
        if(gvoteams.contains(gvoTeamRepository.getReferenceById(id)))
            gvoTeamRepository.delete(gvoTeamRepository.getReferenceById(id));
        return "redirect:/gvoteam";
    }

    @GetMapping("/gvoteambuilder")
    public String gameMonsters(Model model) {

        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());


        model.addAttribute("monster",gameMonsterRepository.findAllMonsters());
        model.addAttribute("gvoteamform",new GvoTeamForm());

        model.addAttribute("isConnected", (user != null));

        return "/html/teamBuilder";
    }

    @PostMapping("/gvoteambuilder")
    public String gameMonsters(@ModelAttribute GvoTeamForm teamForm, RedirectAttributes redirectAttributes) {

        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (teamForm.getMonster1()!=null){
            GvoTeam gvoTeam = new GvoTeam();
            gvoTeam.setUser(user);
            gvoTeam.setMonster1(gameMonsterRepository.getReferenceById(teamForm.getMonster1()));
            if (teamForm.getMonster2()!=null)
                gvoTeam.setMonster2(gameMonsterRepository.getReferenceById(teamForm.getMonster2()));
            if (teamForm.getMonster3()!=null)
                gvoTeam.setMonster3(gameMonsterRepository.getReferenceById(teamForm.getMonster3()));
            gvoTeamRepository.save(gvoTeam);
            return "redirect:/gvoteam";
        }
        redirectAttributes.addFlashAttribute("message",
                "Une erreur est survenu : Selectionner au moins 1 monstre leader'");
        return "redirect:/gvoteambuilder";
    }

}

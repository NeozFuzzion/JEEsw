package projectJEE.sw.controller;

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
import projectJEE.sw.dbEntity.Artifact;
import projectJEE.sw.dbEntity.Rune;
import projectJEE.sw.dbEntity.User;
import projectJEE.sw.dbRepository.ArtifactRepository;
import projectJEE.sw.dbRepository.UserRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class ArtifactsController {
    @Autowired
    ArtifactRepository artifactRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/artifacts")
    public String artifacts(Model model) {
        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<String> jsons =artifactRepository.findAllJson(user);
        if(!jsons.isEmpty()) {
            List<Artifact> artifacts = artifactRepository.findAllByOrderByEfficiencyDesc(jsons.get(0),user);
            float[] efficiency = new float[200];
            int i = 0;
            Iterator<Artifact> it = artifacts.iterator();
            while (it.hasNext() && i < 200) {
                Artifact artifact = it.next();
                efficiency[i] = artifact.getEfficiency();
                i++;
            }
            model.addAttribute("jsons", jsons);
            model.addAttribute("artifacts", artifacts);
            model.addAttribute("eff", efficiency);
        }
        model.addAttribute("data",!jsons.isEmpty());
        return "/html/artifacts";
    }

    @PostMapping("/artifacts/filter")
    public ResponseEntity<JSONObject> artifactFilter(@Param("jsonChosen") String jsonChosen,@Param("artifactType") String artifactType,@Param("nbArtifacts") int nbArtifacts){
        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        JSONObject filter = new JSONObject();
        List<Artifact> artifacts;
        if(artifactType.equals("all")){
            artifacts=artifactRepository.findAllByOrderByEfficiencyDesc(jsonChosen,user);
        }else if(artifactType.equals("allAttributes")){
            artifacts=artifactRepository.findAllByType("Attribute",jsonChosen,user);
        }else if(artifactType.equals("allArchetypes")){
                artifacts=artifactRepository.findAllByType("Archetype",jsonChosen,user);
        }else{
            artifacts=artifactRepository.findAllByRestriction(artifactType,jsonChosen,user);
        }

        Float[] efficiency = new Float[nbArtifacts];
        int i=0;
        Iterator<Artifact> it = artifacts.iterator();
        while(it.hasNext() && i<nbArtifacts){
            Artifact artifact = it.next();
            efficiency[i] = artifact.getEfficiency();
            i++;
        }
        filter.put("jsonToUse",jsonChosen);
        filter.put("efficiency",efficiency);
        filter.put("totalArtifacts",nbArtifacts);
        return ResponseEntity.status(HttpStatus.OK).body(filter);
    }
}

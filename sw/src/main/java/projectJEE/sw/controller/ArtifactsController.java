package projectJEE.sw.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import projectJEE.sw.dbEntity.Artifact;
import projectJEE.sw.dbEntity.Rune;
import projectJEE.sw.dbRepository.ArtifactRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class ArtifactsController {
    @Autowired
    ArtifactRepository artifactRepository;
    @GetMapping("/artifacts")
    public String artifacts(Model model) {
        List<Artifact> artifacts = artifactRepository.findAllByOrderByEfficiencyDesc();
        float[] efficiency = new float[200];
        int i=0;
        Iterator<Artifact> it = artifacts.iterator();
        while(it.hasNext() && i<200){
            Artifact artifact = it.next();
            efficiency[i] = artifact.getEfficiency();
            i++;
        }
        model.addAttribute("artifacts",artifacts);
        model.addAttribute("eff",efficiency);
        return "/html/artifacts";
    }

    @PostMapping("/artifacts/filter")
    public ResponseEntity<JSONObject> artifactFilter(@Param("artifactType") String artifactType,@Param("nbArtifacts") int nbArtifacts){
        JSONObject filter = new JSONObject();
        List<Artifact> artifacts;
        if(artifactType.equals("all")){
            artifacts=artifactRepository.findAllByOrderByEfficiencyDesc();
        }else if(artifactType.equals("allAttributes")){
            artifacts=artifactRepository.findAllByType("Attribute");
        }else if(artifactType.equals("allArchetypes")){
                artifacts=artifactRepository.findAllByType("Archetype");
        }else{
            artifacts=artifactRepository.findAllByRestriction(artifactType);
        }

        Float[] efficiency = new Float[nbArtifacts];
        int i=0;
        Iterator<Artifact> it = artifacts.iterator();
        while(it.hasNext() && i<nbArtifacts){
            Artifact artifact = it.next();
            efficiency[i] = artifact.getEfficiency();
            i++;
        }
        filter.put("efficiency",efficiency);
        filter.put("totalArtifacts",nbArtifacts);
        return ResponseEntity.status(HttpStatus.OK).body(filter);
    }
}

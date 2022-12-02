package projectJEE.sw.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projectJEE.sw.dbEntity.Artifact;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

@Controller
public class ControllerExtract {
    @GetMapping("/")
    public String extract(Model model) throws IOException, ParseException {
        File file = new ClassPathResource("data/NeozFuzzion-840111.json").getFile();

        JSONParser jsonP = new JSONParser();

        JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader(file));
        model.addAttribute("extract", jsonO);
        System.out.println(jsonO.get("unit_list"));
        return "index";

    }
}

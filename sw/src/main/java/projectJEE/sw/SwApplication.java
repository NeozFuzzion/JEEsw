package projectJEE.sw;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import projectJEE.sw.dbEntity.Artifact;
import projectJEE.sw.dbEntity.Rune;
import projectJEE.sw.dbRepository.ArtifactRepository;
import projectJEE.sw.dbRepository.MonsterRepository;
import projectJEE.sw.dbRepository.RuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

@SpringBootApplication
public class SwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwApplication.class, args);
	}

	@Autowired
	MonsterRepository monsterRepository;
	@Autowired
	RuneRepository runeRepository;
	@Autowired
	ArtifactRepository artifactRepository;

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			System.out.println("Démarrage... ");

			System.out.println(runeRepository.findAll());
			System.out.println(monsterRepository.findAll());
			System.out.println(artifactRepository.findAll());
			File file = new ClassPathResource("data/NeozFuzzion-840111.json").getFile();

			JSONParser jsonP = new JSONParser();
			JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader(file));
			JSONArray runes = (JSONArray) jsonO.get("runes");
			JSONArray units=(JSONArray) jsonO.get("unit_list");

			Iterator<JSONObject> itUnits = units.iterator();
			while(itUnits.hasNext()){
				JSONObject element = itUnits.next();
				JSONArray occupiedRunes = (JSONArray) element.get("runes");
				runes.addAll(occupiedRunes);

			}
			Iterator<JSONObject> it = runes.iterator();
			while(it.hasNext()){
				JSONObject element = it.next();
				Rune rune = new Rune();
				long rune_id=(long)element.get("rune_id");
				rune.setClasse((int)element.get("class"));
				rune.setExtra((int)element.get("extra"));
			}



		};
	}
}

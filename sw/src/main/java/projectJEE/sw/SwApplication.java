package projectJEE.sw;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import projectJEE.sw.dbRepository.MonsterRepository;
import projectJEE.sw.dbRepository.RuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileReader;

@SpringBootApplication
public class SwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwApplication.class, args);
	}

	@Autowired
	MonsterRepository monsterRepository;
	@Autowired
	RuneRepository runeRepository;

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			System.out.println("démarrage... ");

			System.out.println(runeRepository.findAll());
			System.out.println(monsterRepository.findAll());
			File file = new ClassPathResource("data/NeozFuzzion-840111.json").getFile();

			JSONParser jsonP = new JSONParser();

				JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader(file));
				System.out.println(jsonO.get("unit_list"));


		};
	}
}

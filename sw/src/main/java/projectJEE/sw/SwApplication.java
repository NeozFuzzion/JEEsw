package projectJEE.sw;

import projectJEE.sw.dbRepository.MonsterRepository;
import projectJEE.sw.dbRepository.RuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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



		};
	}
}

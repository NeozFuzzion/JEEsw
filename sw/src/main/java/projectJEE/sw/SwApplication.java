package projectJEE.sw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class SwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwApplication.class, args);
	}
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver
				= new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(5242880);
		return multipartResolver;
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			System.out.println("Démarrage... ");
		};
	}
}
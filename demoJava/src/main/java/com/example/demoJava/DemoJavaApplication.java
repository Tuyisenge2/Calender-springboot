package com.example.demoJava;

import com.example.demoJava.config.ContentCalendarProperties;
import com.example.demoJava.models.Content;
import com.example.demoJava.models.Status;
import com.example.demoJava.models.Type;
import com.example.demoJava.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@EnableConfigurationProperties(ContentCalendarProperties.class)
@SpringBootApplication
public class DemoJavaApplication {

	public static void main(String[] args) {

       SpringApplication.run(DemoJavaApplication.class, args);
	//	RestTemplate restTemplate =(RestTemplate) context.getBean("restTemplate");
		System.out.println("server started on port 8080");
	}


/*	@Bean
	CommandLineRunner commandLineRunner(ContentRepository repository){
		return args -> {
			Content c = new Content(
					null,
					"my First Blog",
					"desc",
					Status.IDEA,
					Type.VIDEO,
					LocalDateTime.now(),
					null,
					""
			);

			repository.save(c);
		};
	}*/


}

@RestController
class HelloWorldController {

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, World!, Tito, thanks leandre ";
	}
}




package NEWStudentApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class NewStudentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewStudentApiApplication.class, args);
		System.out.println("hello bro");
	}

}

package springcloud.microservices.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroservicesExamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run( MicroservicesExamServiceApplication.class, args );
	}

}

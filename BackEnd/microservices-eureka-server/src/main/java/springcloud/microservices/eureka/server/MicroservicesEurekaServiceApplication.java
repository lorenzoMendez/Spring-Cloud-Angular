package springcloud.microservices.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroservicesEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesEurekaServiceApplication.class, args);
	}

}

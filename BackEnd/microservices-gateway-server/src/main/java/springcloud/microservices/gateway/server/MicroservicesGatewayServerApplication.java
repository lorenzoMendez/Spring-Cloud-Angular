package springcloud.microservices.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroservicesGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesGatewayServerApplication.class, args);
	}

}

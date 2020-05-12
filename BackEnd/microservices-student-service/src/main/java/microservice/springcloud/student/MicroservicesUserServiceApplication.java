package microservice.springcloud.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EntityScan( { "springcloud.microservices.student.common.model" } )
// Hanilitar como cliente eureka
@EnableEurekaClient
@SpringBootApplication
public class MicroservicesUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesUserServiceApplication.class, args);
	}

}

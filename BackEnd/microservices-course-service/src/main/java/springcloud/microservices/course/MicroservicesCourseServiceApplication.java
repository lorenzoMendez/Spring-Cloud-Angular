package springcloud.microservices.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EntityScan( { "springcloud.microservices.student.common.model", "springcloud.microservices.course.model" } )
@EnableEurekaClient
@SpringBootApplication
public class MicroservicesCourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesCourseServiceApplication.class, args);
	}

}

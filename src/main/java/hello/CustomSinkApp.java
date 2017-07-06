package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.app.log.sink.LogSinkConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import({LogSinkConfiguration.class})
@EnableAutoConfiguration
@org.springframework.context.annotation.ImportResource("cache-config.xml")
@EnableGemfireRepositories("hello")
@EnableJpaRepositories("hello")
@EntityScan("hello")
public class CustomSinkApp {

	public static void main(String[] args) {
		SpringApplication.run(CustomSinkApp.class, args);
	}
}

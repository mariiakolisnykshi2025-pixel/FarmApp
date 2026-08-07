//http://localhost:8080/swagger-ui/index.html посилання до свагера


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"Config", "Repository", "Service", "Controller", "Entities"})
@EnableJpaRepositories(basePackages = "Repository")
@EntityScan(basePackages = "Entities")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
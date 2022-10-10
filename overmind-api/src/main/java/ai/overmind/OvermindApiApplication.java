package ai.overmind;

import ai.overmind.config.ApplicationPropertiesComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationPropertiesComponent.class)
public class OvermindApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OvermindApiApplication.class, args);
    }

}

package mn.mlc.elearining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ELeariningApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELeariningApplication.class, args);
    }

}

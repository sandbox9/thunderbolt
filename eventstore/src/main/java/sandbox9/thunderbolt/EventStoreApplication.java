package sandbox9.thunderbolt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by chanwook on 2014. 12. 5..
 */
@ComponentScan(basePackages = "sandbox9.thunderbolt")
@EnableAutoConfiguration
public class EventStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventStoreApplication.class, args);
    }
}

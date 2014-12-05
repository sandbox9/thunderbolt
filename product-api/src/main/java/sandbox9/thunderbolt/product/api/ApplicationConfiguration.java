package sandbox9.thunderbolt.product.api;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo) {
        return new MongoTemplate(mongo, "thunderbolt", new UserCredentials("thunderbolt", "thunderbolt00"));
    }
}

package sandbox9.thunderbolt.config;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Configuration
@EnableMongoRepositories(
        basePackages = "sandbox9.thunderbolt",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*MongoRepository")
)
public class MongoConfiguration {

    @Value("${mongo.username}")
    private String userName;
    @Value("${mongo.password}")
    private String password;

    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo) {
        return new MongoTemplate(mongo, "thunderbolt", new UserCredentials(userName, password));
    }
}

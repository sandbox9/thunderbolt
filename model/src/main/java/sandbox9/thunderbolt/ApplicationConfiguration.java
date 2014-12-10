package sandbox9.thunderbolt;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Configuration
public class ApplicationConfiguration {
    //TODO 분리

    @Value("${mongo.username}")
    private String userName;
    @Value("${mongo.password}")
    private String password;

    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo) {
        return new MongoTemplate(mongo, "thunderbolt", new UserCredentials(userName, password));
    }

//    @Bean
//    TopicExchange topicExchange() {
//        return new TopicExchange(exchangeName);
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(queueName);
//    }
}

package sandbox9.thunderbolt.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chanwook on 2014. 12. 23..
 */
@Configuration
public class QueueConfiguration {
    @Value("${queue.hostname}")
    protected String hostname;

    @Value("${queue.username}")
    protected String userName;

    @Value("${queue.password}")
    protected String password;

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory f = new CachingConnectionFactory(hostname);
        f.setUsername(userName);
        f.setPassword(password);
        f.setVirtualHost(userName);
        return f;
    }

}

package sandbox9.thunderbolt.admin.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sandbox9.thunderbolt.config.QueueConfiguration;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Configuration
public class AdminQueueConfiguration extends QueueConfiguration {

    @Value("${queue.exchangename.product}")
    private String exchangeName;

    @Value("${queue.routingkey.product}")
    private String routingKey;

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory f = new CachingConnectionFactory(hostname);
        f.setUsername(userName);
        f.setPassword(password);
        f.setVirtualHost(userName);
        return f;
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate t = new RabbitTemplate(connectionFactory());
        t.setRoutingKey(this.routingKey);
        t.setExchange(this.exchangeName);
        return t;
    }
}

package sandbox9.thunderbolt.admin.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sandbox9.thunderbolt.message.product.ProductEventKey;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Configuration
public class QueueConfiguration {

    @Value("${queue.hostname}")
    private String hostname;

    @Value("${queue.exchangename}")
    private String exchangeName;

    @Value("${queue.username}")
    private String userName;

    @Value("${queue.password}")
    private String password;

    private String routingKey = ProductEventKey.ETC.name();

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

package sandbox9.thunderbolt.eventstore.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sandbox9.thunderbolt.event.product.listener.ProductEventMessageListener;
import sandbox9.thunderbolt.event.product.repository.ProductEventRepository;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Configuration
public class QueueConfiguration {

    @Value("${queue.hostname}")
    private String hostname;
    @Value("${queue.queuename.product}")
    private String productQueueName;
    @Value("${queue.exchangename}")
    private String exchangeName;
    @Value("${queue.username}")
    private String userName;
    @Value("${queue.password}")
    private String password;

    @Autowired
    ProductEventRepository productEventRepository;

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory f = new CachingConnectionFactory(hostname);
        f.setUsername(userName);
        f.setPassword(password);
        f.setVirtualHost(userName);
        return f;
    }

    @Bean
    SimpleMessageListenerContainer productEvnetlistenerContainer() {
        SimpleMessageListenerContainer c = new SimpleMessageListenerContainer();
        c.setQueueNames(this.productQueueName);
        c.setConnectionFactory(connectionFactory());
        c.setMessageListener(new MessageListenerAdapter(new ProductEventMessageListener(this.productEventRepository)));
        return c;
    }
}

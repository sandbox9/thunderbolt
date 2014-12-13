package sandbox9.thunderbolt.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sandbox9.thunderbolt.event.handler.ProductPricingEventHandler;
import sandbox9.thunderbolt.event.handler.ProductStockEventHandler;
import sandbox9.thunderbolt.event.repository.ProductEventRepository;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Configuration
public class QueueConfiguration {

    @Value("${queue.hostname}")
    private String hostname;
    @Value("${queue.queuename.price}")
    private String priceQueueName;
    @Value("${queue.queuename.stock}")
    private String stockQueueName;
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
    SimpleMessageListenerContainer pricingEvnetlistenerContainer() {
        SimpleMessageListenerContainer c = new SimpleMessageListenerContainer();
        c.setQueueNames(this.priceQueueName);
        c.setConnectionFactory(connectionFactory());
        c.setMessageListener(new MessageListenerAdapter(new ProductPricingEventHandler(this.productEventRepository)));
        return c;
    }

    @Bean
    SimpleMessageListenerContainer stockEvnetlistenerContainer() {
        SimpleMessageListenerContainer c = new SimpleMessageListenerContainer();
        c.setQueueNames(this.stockQueueName);
        c.setConnectionFactory(connectionFactory());
        c.setMessageListener(new MessageListenerAdapter(new ProductStockEventHandler(this.productEventRepository)));
        return c;
    }
}

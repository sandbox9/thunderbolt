package sandbox9.thunderbolt.api.order.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sandbox9.thunderbolt.api.order.listener.OrderReplyMessageListener;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Configuration
public class QueueConfiguration {

    @Value("${queue.hostname}")
    private String hostname;

    @Value("${queue.exchangename.order}")
    private String exchangeName;

    @Value("${queue.routingkey.order}")
    private String routingKey;

    @Value("${queue.queuename.order.reply}")
    private String replyQueue;

    @Value("${queue.username}")
    private String userName;

    @Value("${queue.password}")
    private String password;

    public QueueConfiguration() {
    }

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
        t.setReplyQueue(orderReplyQueue());
        t.setReplyTimeout(5000L); //응답을 5초 기다림
        return t;
    }

    @Bean
    Queue orderReplyQueue() {
        return new Queue(this.replyQueue);
    }

    @Bean
    SimpleMessageListenerContainer orderMessageListener() {
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer();
        listener.setConnectionFactory(connectionFactory());
        listener.setQueues(orderReplyQueue());
        listener.setMessageListener(new MessageListenerAdapter(new OrderReplyMessageListener()));
        return listener;
    }
}

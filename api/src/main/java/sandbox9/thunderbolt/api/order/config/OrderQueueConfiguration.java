package sandbox9.thunderbolt.api.order.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sandbox9.thunderbolt.api.order.listener.order.CartRequestMessageListener;
import sandbox9.thunderbolt.api.order.service.OrderService;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Configuration
public class OrderQueueConfiguration {

    @Value("${queue.order.exchange}")
    private String orderExchangeName;

    @Value("${queue.order.queue.reply}")
    private String orderReplyQueue;

    @Value("${queue.order.queue.cart}")
    private String cartRequestQueue;


    public OrderQueueConfiguration() {
    }

    @Bean
    RabbitTemplate orderQueueTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate t = new RabbitTemplate(connectionFactory);
        t.setExchange(this.orderExchangeName);
        t.setReplyQueue(orderReplyQueue());
        t.setReplyTimeout(5000L); //응답을 5초 기다림
        t.setMessageConverter(new Jackson2JsonMessageConverter());
        return t;
    }

    @Bean
    Queue cartRequestQueue() {
        return new Queue(this.cartRequestQueue);
    }

    @Bean
    Queue orderReplyQueue() {
        return new Queue(this.orderReplyQueue);
    }

    @Bean
    SimpleMessageListenerContainer orderRequestMessageListener(OrderService orderService, ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer();
        listener.setConnectionFactory(connectionFactory);
        listener.setQueues(cartRequestQueue());
        listener.setMessageListener(
                new MessageListenerAdapter(new CartRequestMessageListener(orderService),
                        new Jackson2JsonMessageConverter()));
        return listener;
    }
}

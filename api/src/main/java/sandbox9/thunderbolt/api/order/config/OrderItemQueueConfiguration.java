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
import sandbox9.thunderbolt.api.order.listener.OrderItemRequestMessageListener;
import sandbox9.thunderbolt.api.order.service.OrderService;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Configuration
public class OrderItemQueueConfiguration {

    @Value("${queue.orderitem.exchange}")
    private String orderItemExchange;

    @Value("${queue.orderitem.queue.reply}")
    private String orderItemReplyQueue;

    @Value("${queue.orderitem.queue.request}")
    private String orderItemRequestQueue;

    public OrderItemQueueConfiguration() {
    }

    @Bean
    RabbitTemplate orderQueueTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate t = new RabbitTemplate(connectionFactory);
        t.setExchange(this.orderItemExchange);
        t.setReplyQueue(orderItemReplyQueue());
        t.setReplyTimeout(5000L); //응답을 5초 기다림
        t.setMessageConverter(new Jackson2JsonMessageConverter());
        return t;
    }

    @Bean
    Queue orderItemRequestQueue() {
        return new Queue(this.orderItemRequestQueue);
    }

    @Bean
    Queue orderItemReplyQueue() {
        return new Queue(this.orderItemReplyQueue);
    }

    @Bean
    SimpleMessageListenerContainer orderRequestMessageListener(OrderService orderService, ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer();
        listener.setConnectionFactory(connectionFactory);
        listener.setQueues(orderItemRequestQueue());
        listener.setMessageListener(
                new MessageListenerAdapter(new OrderItemRequestMessageListener(orderService),
                        new Jackson2JsonMessageConverter()));
        return listener;
    }
}

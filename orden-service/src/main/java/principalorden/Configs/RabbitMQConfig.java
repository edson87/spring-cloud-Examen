package principalorden.Configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "orden.exchange";
    public static final String ROUTING_KEY = "orden.created";
    public static final String QUEUE = "orden.queue";
    @Bean
    public TopicExchange exchange(){
       return new TopicExchange(EXCHANGE);
    }
    @Bean
    public Queue queue(){
        return new Queue(QUEUE,true);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

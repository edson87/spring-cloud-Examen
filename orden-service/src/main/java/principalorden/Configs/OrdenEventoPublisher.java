package principalorden.Configs;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import principalcommons.OrdenEvento;

@Component
@RequiredArgsConstructor
public class OrdenEventoPublisher {
    private final RabbitTemplate rabbitTemplate;
    public void publicarOrdenEvento(OrdenEvento evento){
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                evento
        );
        System.out.println("Evento publicado: "+evento);
    }
}

package principalproducto.Configs;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import principalcommons.OrdenEvento;

@Component
public class OrdenEventoListener {
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handlerOrdenEvento(OrdenEvento event){
        System.out.printf("Orden recibida desde RabbitMQ: %s%n", event);
        // Acá se haría todo el proceso que uno necesite hacer con el mensaje recibido.
    }
}

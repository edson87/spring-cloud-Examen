package principalproducto.Configs;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import principalcommons.OrdenEvento;

@Component
public class KafkaOrdenEventoConsumer {
    @KafkaListener(topics = "orden-eventos", groupId = "producto-group")
    public void consumir(OrdenEvento evento){
        System.out.println("Orden Evento recibido de Kafka: "+evento);
    }
}

package principalproducto.Configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import principalcommons.OrdenEvento;

@Component
public class KafkaOrdenEventoConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaOrdenEventoConsumer.class);
    @KafkaListener(topics = "orden-eventos", groupId = "producto-group")
    public void consumir(OrdenEvento evento){
        System.out.println("Orden Evento recibido de Kafka: "+evento);
        logger.info("Orden Evento recibido de Kafka: ", evento);
    }
}

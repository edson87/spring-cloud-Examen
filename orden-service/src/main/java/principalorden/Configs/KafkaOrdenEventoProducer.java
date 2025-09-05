package principalorden.Configs;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import principalcommons.OrdenEvento;

@Component
@RequiredArgsConstructor
public class KafkaOrdenEventoProducer {
    private final KafkaTemplate<String, OrdenEvento> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(KafkaOrdenEventoProducer.class);
    public void enviarOrdenEvento(OrdenEvento ordenEvento){
        kafkaTemplate.send("orden-eventos",ordenEvento);
        System.out.println("Evento enviado a Kafka: "+ordenEvento);
        logger.info("Evento enviado a Kafka: ", ordenEvento);
    }
}

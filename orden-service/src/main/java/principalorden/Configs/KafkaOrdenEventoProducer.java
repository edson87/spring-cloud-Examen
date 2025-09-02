package principalorden.Configs;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import principalcommons.OrdenEvento;

@Component
@RequiredArgsConstructor
public class KafkaOrdenEventoProducer {
    private final KafkaTemplate<String, OrdenEvento> kafkaTemplate;
    public void enviarOrdenEvento(OrdenEvento ordenEvento){
        kafkaTemplate.send("orden-eventos",ordenEvento);
        System.out.println("Evento enviado a Kafka: "+ordenEvento);
    }
}

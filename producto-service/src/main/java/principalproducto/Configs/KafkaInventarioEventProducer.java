package principalproducto.Configs;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaInventarioEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendKafkaInventarioEvent(String message){
        kafkaTemplate.send("inventario-eventos",message);
    }
}

package com.wintux.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaInventarioEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaInventarioEventConsumer.class);

    @KafkaListener(topics = "inventario-eventos", groupId = "producto-group")
    public void consumer(String mensaje){
        logger.info("<<<<<< Oyendo el mensaje Kafka >>>>>");
        System.out.println(mensaje);
    }
}

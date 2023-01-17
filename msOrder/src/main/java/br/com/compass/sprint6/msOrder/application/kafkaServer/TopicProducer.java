package br.com.compass.sprint6.msOrder.application.kafkaServer;

import br.com.compass.sprint6.msOrder.domain.dto.response.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, OrderResponseDTO> kafkaTemplate;

    public void send(OrderResponseDTO message){
        log.info("Payload send: {}", message);
        kafkaTemplate.send(topicName, message);
    }
}

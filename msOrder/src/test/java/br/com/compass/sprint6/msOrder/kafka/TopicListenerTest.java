package br.com.compass.sprint6.msOrder.kafka;

import br.com.compass.sprint6.msOrder.domain.dto.response.AddressResponseDTO;
import br.com.compass.sprint6.msOrder.domain.dto.response.OrderResponseDTO;
import br.com.compass.sprint6.msOrder.framework.adapter.out.event.TopicProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TopicProducerTest {

    @InjectMocks
    private TopicProducer producer;

    @Value("${topic.name.producer}")
    private String topicName;

    @Mock
    private KafkaTemplate<String, OrderResponseDTO> kafkaTemplate;


    @Test
    void sendMessage(){
        producer = new TopicProducer(kafkaTemplate);

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setTotal(new BigDecimal("25"));
        orderResponseDTO.setCpf("09591781555");
        orderResponseDTO.setAddress(new AddressResponseDTO());
        kafkaTemplate.send(topicName,orderResponseDTO);

        producer.send(orderResponseDTO);
    }
}

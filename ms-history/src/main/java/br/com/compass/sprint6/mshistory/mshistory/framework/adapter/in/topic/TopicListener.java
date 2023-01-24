package br.com.compass.sprint6.mshistory.mshistory.framework.adapter.in.topic;

import br.com.compass.sprint6.mshistory.mshistory.application.service.HistoryService;
import br.com.compass.sprint6.mshistory.mshistory.application.service.mapper.HistoryInputDisassembler;
import br.com.compass.sprint6.mshistory.mshistory.domain.dto.request.HistoryRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

    private final HistoryService service;

    private final HistoryInputDisassembler disassembler;

    @Value("${topic.name.consumer")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, HistoryRequestDTO> payload) {
        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());

        service.create(payload.value());
    }
}
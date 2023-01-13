package br.com.compass.sprint6.msOrder.service;

import br.com.compass.sprint6.msOrder.entities.Item;
import br.com.compass.sprint6.msOrder.exceptions.response.InvalidDateException;
import br.com.compass.sprint6.msOrder.exceptions.response.ItemNotFoundException;
import br.com.compass.sprint6.msOrder.repository.ItemRepository;
import br.com.compass.sprint6.msOrder.service.assembler.ItemDTOAssembler;
import br.com.compass.sprint6.msOrder.service.assembler.ItemInputDisassembler;
import br.com.compass.sprint6.msOrder.service.dto.request.ItemRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResponseDTO;
import br.com.compass.sprint6.msOrder.service.dto.response.ItemResumeResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemDTOAssembler assembler;
    private final ItemRepository repository;

    public Item fetchOrFail(Long id) {
        log.info("Chamando método fetchOrFail (ID) - Service Order");
        return repository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public ItemResumeResponseDTO updateItem(Long id) {
        log.info("Chamando método updateItem - Service Order");
        Item item = fetchOrFail(id);
        item = create(item);
        return assembler.toResumeModel(item);
    }

    @Transactional
    public Item create(Item item) {
        log.info("Chamando método create (salvando no repository) - Service Item");
        return repository.save(item);
    }

    public void verifyDate(OrderRequestDTO request) {
        log.info("Chamando método verifyDate - Service Item");
        request.getItems()
                .forEach(
                        data -> {
                            if (data.getCreation().isAfter(data.getExpiration())) {
                                throw new InvalidDateException();
                            }
                        }
                );
    }

    public void merge(Map<String, Object> sourceData, Item itemDestiny) {
        ObjectMapper objectMapper = new ObjectMapper();
        Item itemOrigin = objectMapper.convertValue(sourceData, Item.class);

        sourceData.forEach((propertyName, propertyValue) -> {
            Field field = ReflectionUtils.findField(Item.class, propertyName);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, itemOrigin);

            ReflectionUtils.setField(field, itemDestiny, newValue);
        });
    }
}

package br.com.compass.sprint6.msOrder.application.service;

import br.com.compass.sprint6.msOrder.domain.dto.request.ItemRequestDTO;
import br.com.compass.sprint6.msOrder.domain.dto.request.OrderRequestDTO;
import br.com.compass.sprint6.msOrder.domain.dto.response.ItemResumeResponseDTO;
import br.com.compass.sprint6.msOrder.domain.model.Item;
import br.com.compass.sprint6.msOrder.framework.exceptions.response.InvalidDateException;
import br.com.compass.sprint6.msOrder.framework.exceptions.response.ItemNotFoundException;
import br.com.compass.sprint6.msOrder.framework.adapter.out.database.ItemRepository;
import br.com.compass.sprint6.msOrder.application.service.mapper.ItemDTOAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemDTOAssembler assembler;
    private final ItemRepository repository;

    public Item fetchOrFail(Long id) {
        log.info("Chamando método fetchOrFail (ID) - Service Item");
        return repository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public ItemResumeResponseDTO updateItem(Map<String, Object> fields, Long id) {
        log.info("Chamando método updateItem - Service Item");
        Item item = fetchOrFail(id);
        merge(fields, item);
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
        request.getItems().forEach(data -> {
            if (data.getCreation().isAfter(data.getExpiration())) {
                throw new InvalidDateException();
            }
        });
    }

    public void getTotal(OrderRequestDTO request) {
        log.info("Chamando método getTotal - Service Item");
        BigDecimal b = new BigDecimal("0");
        List<ItemRequestDTO> items = request.getItems();

        for(ItemRequestDTO itemRequestDTO : items){
            b = b.add(itemRequestDTO.getPrice());
            request.setTotal(b);
        }

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

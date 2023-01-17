package br.com.compass.sprint6.msOrder.framework.adapter.out.database;

import br.com.compass.sprint6.msOrder.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}

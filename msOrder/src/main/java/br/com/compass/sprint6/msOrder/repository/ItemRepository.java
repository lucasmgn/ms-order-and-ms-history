package br.com.compass.sprint6.msOrder.repository;

import br.com.compass.sprint6.msOrder.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}

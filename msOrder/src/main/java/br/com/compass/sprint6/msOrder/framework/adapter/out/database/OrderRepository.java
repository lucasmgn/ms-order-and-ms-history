package br.com.compass.sprint6.msOrder.framework.adapter.out.database;

import br.com.compass.sprint6.msOrder.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCpf(String cpf);

}

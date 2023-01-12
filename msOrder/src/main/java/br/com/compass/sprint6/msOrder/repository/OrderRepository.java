package br.com.compass.sprint6.msOrder.repository;

import br.com.compass.sprint6.msOrder.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCpf(String cpf);

}

package br.com.compass.sprint6.msOrder.framework.adapter.out.database;

import br.com.compass.sprint6.msOrder.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCepAndNumber(String cep, String number);
}

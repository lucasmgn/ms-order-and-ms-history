package br.com.compass.sprint6.mshistory.mshistory.framework.adapter.out.database;

import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepository extends MongoRepository<History,String> {

    List<History> findByDateBetween(LocalDate form, LocalDate to);
}

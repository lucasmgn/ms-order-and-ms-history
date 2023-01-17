package br.com.compass.sprint6.mshistory.mshistory.framework.adapter.out.database;

import br.com.compass.sprint6.mshistory.mshistory.domain.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History,String> {
}

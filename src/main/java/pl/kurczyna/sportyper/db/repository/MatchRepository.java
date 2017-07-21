package pl.kurczyna.sportyper.db.repository;

import org.springframework.data.repository.CrudRepository;
import pl.kurczyna.sportyper.db.MatchEntity;

public interface MatchRepository extends CrudRepository<MatchEntity, Long> {

}

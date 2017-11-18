package pl.kurczyna.sportyper.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import pl.kurczyna.sportyper.db.PlayerEntity;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findById(Long id);
    List<PlayerEntity> findAll();
    Optional<PlayerEntity> findByName(String name);
}

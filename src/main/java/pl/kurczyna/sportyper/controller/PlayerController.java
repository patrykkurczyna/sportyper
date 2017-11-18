package pl.kurczyna.sportyper.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kurczyna.sportyper.db.PickEntity;
import pl.kurczyna.sportyper.db.PlayerEntity;
import pl.kurczyna.sportyper.db.repository.PlayerRepository;
import pl.kurczyna.sportyper.dto.Pick;
import pl.kurczyna.sportyper.dto.Player;

import static pl.kurczyna.sportyper.dto.json.Views.In;
import static pl.kurczyna.sportyper.dto.json.Views.Out;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository repository;

    @PostMapping("players")
    public @JsonView(Out.class)
    ResponseEntity<Player> addPlayer(@JsonView(In.class) @RequestBody Player player) {
        return ResponseEntity.ok(repository.findByName(player.getName()).orElseGet(() -> {
            PlayerEntity entity = PlayerEntity.of(player);
            return repository.save(entity);
        }).toDto());
    }

    @GetMapping("players/{id}")
    public @JsonView(Out.class)
    ResponseEntity<Player> getById(@PathVariable("id") Long id) {
        return repository.findById(id)
                .map(playerEntity -> ResponseEntity.ok(playerEntity.toDto()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("players/{id}/picks")
    public @JsonView(Out.class)
    ResponseEntity<List<Pick>> getPlayersPicks(@PathVariable("id") Long id) {
        return repository.findById(id)
                .map(playerEntity -> ResponseEntity.ok(
                        playerEntity.getPicks().stream()
                                .map(PickEntity::toDto)
                                .collect(Collectors.toList())
                        )
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("players")
    public @JsonView(Out.class)
    ResponseEntity<List<Player>> getAll() {
        return ResponseEntity.ok(repository.findAll().stream()
                .map(PlayerEntity::toDto)
                .collect(Collectors.toList()));
    }
}


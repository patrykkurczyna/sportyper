package pl.kurczyna.sportyper.controller;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kurczyna.sportyper.db.MatchEntity;
import pl.kurczyna.sportyper.db.PickEntity;
import pl.kurczyna.sportyper.db.PlayerEntity;
import pl.kurczyna.sportyper.db.repository.MatchRepository;
import pl.kurczyna.sportyper.db.repository.PickRepository;
import pl.kurczyna.sportyper.db.repository.PlayerRepository;
import pl.kurczyna.sportyper.dto.Pick;
import pl.kurczyna.sportyper.dto.json.Views;

@RestController
public class PickController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PickRepository pickRepository;

    @PostMapping("picks/{matchId}")
    public @JsonView(Views.Out.class) ResponseEntity<Pick> addPick(@RequestBody @JsonView(Views.In.class) Pick pick, @RequestParam("playerId") Long playerId,
                                                                   @PathVariable("matchId") Long matchId) {
        Optional<MatchEntity> maybeMatch = matchRepository.findById(matchId);
        Optional<PlayerEntity> maybePlayer = playerRepository.findById(playerId);
        if (maybeMatch.isPresent() && maybePlayer.isPresent()) {
            PickEntity pickEntity = PickEntity.of(pick, maybeMatch.get(), maybePlayer.get());
            return ResponseEntity.ok(pickRepository.save(pickEntity).toDto());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

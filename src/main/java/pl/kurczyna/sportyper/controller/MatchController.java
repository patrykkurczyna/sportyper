package pl.kurczyna.sportyper.controller;

import javax.websocket.server.PathParam;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurczyna.sportyper.db.GoalEntity;
import pl.kurczyna.sportyper.db.MatchEntity;
import pl.kurczyna.sportyper.db.MatchResultEntity;
import pl.kurczyna.sportyper.db.repository.MatchRepository;
import pl.kurczyna.sportyper.dto.Match;

@RestController("matches")
public class MatchController {

    @Autowired
    private MatchRepository repository;

    @GetMapping
    public MatchEntity getById(@PathParam("id") Long id) {
        return repository.findOne(id);
    }

    @PostMapping()
    public ResponseEntity<Match> addMatch() {
        MatchResultEntity result = MatchResultEntity.builder()
                .awayFTScore(0)
                .homeFTScore(4)
                .homeHTScore(3)
                .awayHTScore(0)
                .build();
        result.addGoals(Arrays.asList(
                GoalEntity.builder().minute(3).scorer("Marchisio").build(),
                GoalEntity.builder().minute(31).scorer("Dybala").build(),
                GoalEntity.builder().minute(44).scorer("Bernardeschi").build(),
                GoalEntity.builder().minute(78).scorer("Costa").build())
        );
        MatchEntity matchEntity = MatchEntity.builder()
                .homeTeam("Juventus")
                .awayTeam("Inter")
                .startTime(LocalDateTime.now())
                .result(result)
                .build();
        return ResponseEntity.ok(repository.save(matchEntity).toDto());
    }

}

package pl.kurczyna.sportyper.controller;

import java.time.LocalDateTime;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kurczyna.sportyper.db.GoalEntity;
import pl.kurczyna.sportyper.db.MatchEntity;
import pl.kurczyna.sportyper.db.MatchResultEntity;
import pl.kurczyna.sportyper.db.repository.MatchRepository;
import pl.kurczyna.sportyper.dto.Match;

import static pl.kurczyna.sportyper.dto.json.Views.In;
import static pl.kurczyna.sportyper.dto.json.Views.Out;

@RestController
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("matches/{id}")
    public @JsonView(Out.class)
    ResponseEntity<Match> getById(@PathVariable("id") Long id) {
        return matchRepository.findById(id)
                .map(matchEntity -> ResponseEntity.ok(matchEntity.toDto()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("matches")
    public @JsonView(Out.class)
    ResponseEntity<Match> addMatch(@RequestBody @JsonView(In.class) Match match) {
        MatchEntity entity = MatchEntity.of(match);
        return ResponseEntity.ok(matchRepository.save(entity).toDto());
    }

    @PostMapping("matches/new")
    public @JsonView(Out.class)
    ResponseEntity<Match> addMatch() {
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
        return ResponseEntity.ok(matchRepository.save(matchEntity).toDto());
    }

}

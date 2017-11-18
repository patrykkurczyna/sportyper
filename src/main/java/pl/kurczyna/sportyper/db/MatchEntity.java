package pl.kurczyna.sportyper.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.kurczyna.sportyper.dto.Match;

@Entity
@Builder
@Data
@Table(name = "sportyper_matches")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDateTime startTime;

    @Size(max = 40)
    @NotNull
    private String homeTeam;

    @Size(max = 40)
    @NotNull
    private String awayTeam;

    @Size(max = 80)
    private String venue;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private MatchResultEntity result = null;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PickEntity> picks = new ArrayList<>();

    public Match toDto() {
        return Match.builder()
                .id(id)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .startTime(startTime)
                .venue(venue)
                .result(result == null ? null : result.toDto())
                .build();
    }

    public static MatchEntity of(Match match) {
        return MatchEntity.builder()
                .homeTeam(match.getHomeTeam())
                .awayTeam(match.getAwayTeam())
                .startTime(match.getStartTime())
                .venue(match.getVenue())
                .build();
    }

    public MatchEntity addPick(PickEntity pick) {
        this.picks.add(pick);
        return this;
    }
}

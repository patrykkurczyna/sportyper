package pl.kurczyna.sportyper.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kurczyna.sportyper.dto.Match;
import pl.kurczyna.sportyper.dto.MatchInput;

@Entity
@Builder
@Data
@Table(name = "sportyper_matches")
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

    public static MatchEntity of(MatchInput match) {
        return MatchEntity.builder()
                .homeTeam(match.getHomeTeam())
                .awayTeam(match.getAwayTeam())
                .startTime(match.getStartTime())
                .venue(match.getVenue())
                .build();
    }
}

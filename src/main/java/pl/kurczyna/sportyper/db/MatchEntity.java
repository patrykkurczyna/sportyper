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

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import pl.kurczyna.sportyper.dto.Match;

@Entity
@Builder
@Data
@Table(name = "sportyper_matches")
public class MatchEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
//    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
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
    private MatchResultEntity result;

    public Match toDto() {
        return Match.builder()
                .id(id)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .startTime(startTime)
                .venue(venue)
                .result(result.toDto())
                .build();
    }
}

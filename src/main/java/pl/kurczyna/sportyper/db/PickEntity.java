package pl.kurczyna.sportyper.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.kurczyna.sportyper.dto.Pick;

@Entity
@Builder
@Table(name = "sportyper_picks")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PickEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Min(0)
    @Column(name = "home_ht_score")
    private Integer homeHTScore;

    @NotNull
    @Min(0)
    @Column(name = "home_ft_score")
    private Integer homeFTScore;

    @Min(0)
    @Column(name = "away_ht_score")
    private Integer awayHTScore;

    @NotNull
    @Min(0)
    @Column(name = "away_ft_score")
    private Integer awayFTScore;

    @Size(max = 80)
    private String toScore;

    @Size(max = 80)
    private String firstToScore;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id")
    @NotNull
    private MatchEntity match;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    @NotNull
    private PlayerEntity player;

    public static PickEntity of(Pick pick, MatchEntity matchEntity, PlayerEntity playerEntity) {
        return PickEntity.builder()
                .homeFTScore(pick.getHomeFTScore())
                .homeHTScore(pick.getHomeHTScore())
                .awayFTScore(pick.getAwayFTScore())
                .awayHTScore(pick.getAwayHTScore())
                .toScore(pick.getToScore())
                .firstToScore(pick.getFirstToScore())
                .player(playerEntity)
                .match(matchEntity)
                .build();
    }

    public Pick toDto() {
        return Pick.builder()
                .id(id)
                .homeFTScore(homeFTScore)
                .homeHTScore(homeHTScore)
                .awayFTScore(awayFTScore)
                .awayHTScore(awayHTScore)
                .toScore(toScore)
                .firstToScore(firstToScore)
                .matchId(match.getId())
                .playerId(player.getId())
                .build();
    }
}

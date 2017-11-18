package pl.kurczyna.sportyper.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.kurczyna.sportyper.dto.MatchResult;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "sportyper_match_results")
public class MatchResultEntity {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Min(0)
    @NotNull
    @Column(name = "home_ht_score")
    private Integer homeHTScore;

    @Min(0)
    @NotNull
    @Column(name = "away_ht_score")
    private Integer awayHTScore;

    @Min(0)
    @NotNull
    @Column(name = "home_ft_score")
    private Integer homeFTScore;

    @Min(0)
    @NotNull
    @Column(name = "away_ft_score")
    private Integer awayFTScore;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<GoalEntity> goals = new ArrayList<>();

    @OneToOne(mappedBy = "result")
    @JsonIgnore
    private MatchEntity matchEntity;

    public void addGoal(GoalEntity goal) {
        goal.setResult(this);
        this.goals.add(goal);
    }

    public void addGoals(List<GoalEntity> goals) {
        goals.forEach(this::addGoal);
    }

    public MatchResult toDto() {
        return MatchResult.builder()
                .awayFTScore(awayFTScore)
                .awayHTScore(awayHTScore)
                .homeFTScore(homeFTScore)
                .homeHTScore(homeHTScore)
                .goals(goals.stream().map(GoalEntity::toDto).collect(Collectors.toList()))
                .build();
    }
}

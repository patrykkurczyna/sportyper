package pl.kurczyna.sportyper.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.kurczyna.sportyper.dto.Goal;

@Entity
@Builder
@Data
@Table(name = "sportyper_goals")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoalEntity {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Min(1)
    @Max(120)
    @NotNull
    private int minute;

    @Size(max = 80)
    @NotNull
    private String scorer;

    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    @JsonIgnore
    private MatchResultEntity result;

    public Goal toDto() {
        return Goal.builder()
                .minute(minute)
                .scorer(scorer)
                .build();
    }
}

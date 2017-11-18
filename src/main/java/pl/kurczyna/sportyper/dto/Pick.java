package pl.kurczyna.sportyper.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static pl.kurczyna.sportyper.dto.json.Views.Out;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pick {
    @JsonView(Out.class)
    private Long id;

    @Min(0)
    private Integer homeHTScore;

    @NotNull
    @Min(0)
    private Integer homeFTScore;

    @Min(0)
    private Integer awayHTScore;

    @NotNull
    @Min(0)
    private Integer awayFTScore;

    @Size(max = 80)
    private String toScore;

    @Size(max = 80)
    private String firstToScore;

    @NotNull
    @JsonView(Out.class)
    private Long matchId;

    @NotNull
    @JsonView(Out.class)
    private Long playerId;
}

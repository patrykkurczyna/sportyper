package pl.kurczyna.sportyper.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResult {
    private Integer homeHTScore;
    private Integer awayHTScore;
    private Integer homeFTScore;
    private Integer awayFTScore;
    private List<Goal> goals;
}

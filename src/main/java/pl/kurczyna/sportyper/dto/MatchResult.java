package pl.kurczyna.sportyper.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResult {
    private int homeHTScore;
    private int awayHTScore;
    private int homeFTScore;
    private int awayFTScore;
    private List<Goal> goals;
}

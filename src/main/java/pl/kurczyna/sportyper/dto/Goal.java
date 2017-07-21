package pl.kurczyna.sportyper.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Goal {
    private int minute;
    private String scorer;
}

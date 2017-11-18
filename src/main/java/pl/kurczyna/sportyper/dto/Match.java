package pl.kurczyna.sportyper.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kurczyna.sportyper.dto.json.Views;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @JsonView(Views.Out.class)
    private Long id;
    private String homeTeam;
    private String awayTeam;
    private String venue;
    @JsonView(Views.Out.class)
    private MatchResult result;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
}

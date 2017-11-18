package pl.kurczyna.sportyper.dto;

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
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @JsonView(Out.class)
    private Long id;

    @NotNull
    @Size(max = 40)
    private String name;
}

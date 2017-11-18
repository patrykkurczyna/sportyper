package pl.kurczyna.sportyper.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.kurczyna.sportyper.dto.Player;

@Entity
@Builder
@Data
@Table(name = "sportyper_players")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PickEntity> picks = new ArrayList<>();

    public static PlayerEntity of(Player player) {
        return PlayerEntity.builder()
                .name(player.getName())
                .build();
    }

    public Player toDto() {
        return Player.builder()
                .id(id)
                .name(name)
                .build();
    }
}

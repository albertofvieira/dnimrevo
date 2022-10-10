package ai.overmind.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@NoArgsConstructor
@AllArgsConstructor
public class ImdbFilmeDTO {

    private List<FilmeDTO> pioresFilmes;
}

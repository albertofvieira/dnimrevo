package ai.overmind.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"nota", "titulo", "autor", "conteudo"})
public class ComentarioDTO {

    private String titulo;
    private Double nota;
    private String conteudo;
    private String autor;
}

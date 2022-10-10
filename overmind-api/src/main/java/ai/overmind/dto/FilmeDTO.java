package ai.overmind.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "nome", "nota", "url", "diretores", "elenco", "comentarios"})
public class FilmeDTO {

    private Integer id;
    private String nome;
    private String nota;
    private String url;
    private List<ComentarioDTO> comentarios;
    private HashSet<String> diretores = new HashSet<>();
    private HashSet<String> elenco = new HashSet<>();
}

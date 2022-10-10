package ai.overmind.service;

import ai.overmind.config.ApplicationPropertiesComponent;
import ai.overmind.dto.ComentarioDTO;
import ai.overmind.dto.FilmeDTO;
import ai.overmind.dto.ImdbFilmeDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ImdbCrawlerService {

    @Autowired
    ApplicationPropertiesComponent applicationPropertiesComponent;

    public ImdbFilmeDTO getPioresFilmes() {
        ImdbFilmeDTO imdbFilmeDTODTO = new ImdbFilmeDTO();

        try {
            Document document = generateDocument("/chart/bottom");
            Elements elementList = document.select("tbody[class='lister-list']");

            imdbFilmeDTODTO.setPioresFilmes(
                    extractPioresFilmes(elementList)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imdbFilmeDTODTO;
    }

    /**
     * Mapeamento dos 10 piores filmes em ordem decrescente
     * @param elementList
     * @return List<FilmeDTO>
     */
    private List<FilmeDTO> extractPioresFilmes(Elements elementList) {
        List<FilmeDTO> filmeList = new ArrayList<>();

        for (Element element : elementList.select("tr").subList(0, 10)) {
            FilmeDTO filmeDTO = new FilmeDTO();

            // Id
            filmeDTO.setId(Integer.parseInt(
                    element.getElementsByClass("posterColumn")
                            .get(0)
                            .selectFirst("span[name='rk']")
                            .attr("data-value"))
            );

            // Nome
            filmeDTO.setNome(
                    element.getElementsByClass("titleColumn")
                            .get(0)
                            .getElementsByAttribute("href").text()
            );

            // URL
            filmeDTO.setUrl(
                    element.getElementsByClass("posterColumn")
                            .get(0)
                            .selectFirst("a")
                            .attr("href")
            );

            // Nota
            filmeDTO.setNota(
                    element.getElementsByClass("ratingColumn imdbRating").text()
            );

            // Diretores
            Document document = generateDocument(filmeDTO.getUrl());

            for(Element e : document.select("a[href$=\"ref_=tt_ov_dr\"]")){
                filmeDTO.getDiretores().add(
                        e.getElementsByTag("a").text()
                );
            }

            // Elenco
            for(Element e : document.select("a[href$=\"ref_=tt_ov_st\"]")){
                filmeDTO.getElenco().add(
                        e.getElementsByTag("a").text()
                );
            }

            // Comentários
            adicionarComentarios(filmeDTO);

            filmeList.add(filmeDTO);
        }

        return filmeList;
    }

    private void adicionarComentarios(FilmeDTO filmeDTO) {
        String [] path = filmeDTO.getUrl().split("/");
        Document dom = generateDocument("/title/"+path[2]+"/reviews?ref_=tt_urv");
        Elements commentsDiv = dom.select("div[class='lister-list']");
        List<ComentarioDTO> comentarioLista = new ArrayList<>();

        for(Element element : commentsDiv.select("div[class^='lister-item mode-detail imdb-user-review ']")) {
            ComentarioDTO comentarioDTO = new ComentarioDTO();
            if(checkHasRating(element)) {
                Double rate = convertRateToNumber(element.getElementsByClass("ipl-ratings-bar").text());
                if(checkGreaterEqualThanFive(rate)) {
                    comentarioDTO = criarComentario(element,rate);
                    comentarioLista.add(comentarioDTO);
                }
            }

        }
        filmeDTO.setComentarios(comentarioLista);
    }

    private ComentarioDTO criarComentario(Element element, Double rate) {
        ComentarioDTO comments = new ComentarioDTO();
        comments.setTitulo(element.getElementsByClass("lister-item-content").get(0).getElementsByClass("title").text());
        comments.setConteudo(element.getElementsByClass("text show-more__control").text());
        comments.setNota(rate);
        comments.setAutor(element.getElementsByClass("display-name-link").text());
        return comments;
    }

    private Boolean checkHasRating(Element element) {
        return element.getElementsByClass("ipl-ratings-bar").hasText() ? true : false;
    }

    private Boolean checkGreaterEqualThanFive(Double number) {
        return number > 4 ? true : false;
    }

    private Double convertRateToNumber(String rate) {
        String [] arrayValues = rate.split("/");
        Double number = Double.parseDouble(arrayValues[0]);
        return number;
    }
    
    private Document generateDocument(String URL){
        try {
            return Jsoup
                    .connect(applicationPropertiesComponent.getImdbRootURL() + URL)
                    .header("Accept-Language", applicationPropertiesComponent.getImdbDefaultLanguage())
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

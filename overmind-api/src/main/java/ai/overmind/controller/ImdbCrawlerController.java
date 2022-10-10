package ai.overmind.controller;

import ai.overmind.service.ImdbCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crawler/imdb")
public class ImdbCrawlerController {

    @Autowired
    private ImdbCrawlerService overmindCrawlerService;

    @GetMapping("piores")
    public ResponseEntity<Object> getPioresFilmes(){
        return new ResponseEntity<>(
                overmindCrawlerService.getPioresFilmes(),
                HttpStatus.OK
        );
    }
}

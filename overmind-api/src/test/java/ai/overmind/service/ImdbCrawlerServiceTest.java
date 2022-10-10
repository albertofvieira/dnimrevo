package ai.overmind.service;

import ai.overmind.config.ApplicationPropertiesComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImdbCrawlerServiceTest {
    @Autowired
    private ImdbCrawlerService imdbCrawlerService;

    @Mock
    ApplicationPropertiesComponent applicationPropertiesComponent;

    @Test
    public void shouldReturnListaPioresFilmes(){
        Assertions.assertNotNull(
                imdbCrawlerService.getPioresFilmes()
        );
    }
}
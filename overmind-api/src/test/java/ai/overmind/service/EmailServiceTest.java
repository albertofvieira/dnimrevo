package ai.overmind.service;

import ai.overmind.config.ApplicationPropertiesComponent;
import ai.overmind.dto.EmailDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Mock
    private ApplicationPropertiesComponent applicationPropertiesComponent;

    @Test
    public void shouldSendEmailSuccessfully(){
        EmailDTO emailDTO = new EmailDTO(
                "Alberto Vieira TESTE",
                "alberto@teste.com",
                "11988887777",
                "Teste@1234"
        );

        Assertions.assertTrue(
                emailService.sendEmail(emailDTO)
        );
    }
}
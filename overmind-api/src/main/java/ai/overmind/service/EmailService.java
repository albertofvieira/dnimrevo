package ai.overmind.service;

import ai.overmind.config.ApplicationPropertiesComponent;
import ai.overmind.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    
    @Autowired
    private ApplicationPropertiesComponent applicationPropertiesComponent;

    public boolean sendEmail(EmailDTO emailDTO){
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", applicationPropertiesComponent.getMailSmtpHost());
            props.put("mail.smtp.port", applicationPropertiesComponent.getMailSmtpPort());
            props.put("mail.smtp.auth", applicationPropertiesComponent.getMailSmtpAuth());
            props.put("mail.smtp.starttls.enable", applicationPropertiesComponent.getMailSmtpTlsEnable());

            // Get the Session object.
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    applicationPropertiesComponent.getMailApiKey(),
                                    applicationPropertiesComponent.getMailPassword()
                            );
                        }
                    });

            Message message = new MimeMessage(session);
            message.setSubject(applicationPropertiesComponent.getMailSubject());
            message.setFrom(new InternetAddress(applicationPropertiesComponent.getMailFrom()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(applicationPropertiesComponent.getMailTo())
            );

            message.setText("Olá,"
                    + "\n\n esta é uma mensagem enviada pelo applicativo desenvolvido por Alberto Vieira."
                    + "\n\nNome: " + emailDTO.getNome()
                    + "\nEmail: " + emailDTO.getEmail()
                    + "\nSenha: " + emailDTO.getPassword()
                    + "\nTelefone: " + emailDTO.getTelefone()
                            + "\n\nAtencionsamente,\nAlberto.");

            // Send message
            Transport.send(message);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

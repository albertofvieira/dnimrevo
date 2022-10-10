package ai.overmind.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "overmind")
public class ApplicationPropertiesComponent {

    private String imdbRootURL;
    private String imdbDefaultLanguage;
    private String mailSmtpHost;
    private String mailSmtpPort;
    private String mailSmtpAuth;
    private String mailSmtpTlsEnable;
    private String mailApiKey;
    private String mailPassword;
    private String mailSubject;
    private String mailTo;
    private String mailFrom;
}
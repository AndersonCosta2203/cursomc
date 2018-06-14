package br.com.cursomc.config;

import br.com.cursomc.services.DBService;
import br.com.cursomc.services.EmailService;
import br.com.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev") // Para utilizar o profile de test configurado no arquivo application.properties
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean // Indica que o Spring deve invocar este método e gerenciar o objeto retornado por ele.
    public boolean instantiateDatabase() throws ParseException {

        if (!"create".equals(strategy)) {
            return false;
        }

        dbService.instantiateTestDataBase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}

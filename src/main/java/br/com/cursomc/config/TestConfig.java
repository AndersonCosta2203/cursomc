package br.com.cursomc.config;

import br.com.cursomc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test") // Para utilizar o profile de test configurado no arquivo application.properties
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean // Indica que o Spring deve invocar este método e gerenciar o objeto retornado por ele.
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDataBase();
        return true;
    }
}

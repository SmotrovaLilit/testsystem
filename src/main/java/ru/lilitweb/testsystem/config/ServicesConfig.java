package ru.lilitweb.testsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.lilitweb.testsystem.service.*;

import java.util.Locale;
import java.util.Map;

@Configuration
public class ServicesConfig {

    @Autowired
    Environment env;

    @Bean
    public LocalisationService localisationService(MessageSource source) {
        return new LocalisationServiceImpl(source);
    }

    @Bean
    public TestInputService inputService() {
        return new ConsoleTestInputService(System.in);
    }

    @Bean
    public TestOutputService outputService(LocalisationService localisationService) {
        return new ConsoleTestOutputService(System.out, localisationService);
    }

    @Bean
    public FileResolverService fileResolverService(LocalisationService localisationService) {
        Locale locale = localisationService.getLocale();
        String configName = "questions.file." + locale.getLanguage();
        if (env.getProperty(configName) != null) {
            return new FileResolverServiceImpl(configName);
        }

        return new FileResolverServiceImpl(env.getProperty("questions.file.default"));
    }

    @Bean
    public QuestionsLoaderService csvTestsLoaderService(FileResolverService fileResolverService) {
        return new CsvTestsLoaderService(fileResolverService);
    }
}

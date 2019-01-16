package ru.lilitweb.testsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import ru.lilitweb.testsystem.service.*;

import java.util.Locale;
import java.util.Map;

@Configuration
public class ServicesConfig {
    @Autowired
    private ResourceLoader resourceLoader;

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
    public FileResolverService fileResolverService(LocalisationService localisationService, QuestionsProperties properties) {
        Locale locale = localisationService.getLocale();

        Map<String, String> files = properties.getFiles();
        String fileName = files.get(locale.getLanguage());
        if (fileName != null) {
            return new FileResolverServiceImpl(fileName, resourceLoader);
        }


        return new FileResolverServiceImpl(files.get("default"), resourceLoader);
    }

    @Bean
    public QuestionsLoaderService csvTestsLoaderService(FileResolverService fileResolverService) {
        return new CsvTestsLoaderService(fileResolverService);
    }
}

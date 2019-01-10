package ru.lilitweb.testsystem;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lilitweb.testsystem.service.QuestionsLoaderService;
import ru.lilitweb.testsystem.service.TestInputException;
import ru.lilitweb.testsystem.service.TestService;

import java.io.*;
import java.util.Objects;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) throws TestInputException, IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        QuestionsLoaderService questionsLoaderService = context.getBean(QuestionsLoaderService.class);
        TestService testService = context.getBean(TestService.class);

        try (BufferedReader reader = getCsvFileReader()) {
            Report report = testService.process(questionsLoaderService.getQuestions(reader));
            testService.print(report);
        }

    }

    private static BufferedReader getCsvFileReader() throws FileNotFoundException {
        String csvFile = "questions.csv";
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(csvFile)).getFile());
        return new BufferedReader(new FileReader(file));
    }
}

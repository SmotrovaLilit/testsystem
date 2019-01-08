package ru.lilitweb.testsystem;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lilitweb.testsystem.service.QuestionsLoaderService;
import ru.lilitweb.testsystem.service.TestInputException;
import ru.lilitweb.testsystem.service.TestService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TestInputException, IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionsLoaderService questionsLoaderService = context.getBean(QuestionsLoaderService.class);
        TestService testService = context.getBean(TestService.class);

        Report report = testService.process(questionsLoaderService.getQuestions());
        testService.print(report);
    }
}

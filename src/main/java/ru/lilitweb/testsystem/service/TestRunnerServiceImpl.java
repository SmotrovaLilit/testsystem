package ru.lilitweb.testsystem.service;

import org.springframework.stereotype.Service;
import ru.lilitweb.testsystem.models.ReportModel;
import ru.lilitweb.testsystem.models.QuestionModel;

import java.io.*;
import java.util.List;

@Service
public class TestRunnerServiceImpl implements TestRunnerService {
    private TestInputService inputService;
    private TestOutputService outputService;
    private QuestionsLoaderService questionsLoaderService;
    private FileResolverService fileResolverService;

    public TestRunnerServiceImpl(
            TestInputService inputService,
            TestOutputService outputService,
            QuestionsLoaderService testLoaderService,
            FileResolverService fileResolverService
    ) {
        this.inputService = inputService;
        this.outputService = outputService;
        this.questionsLoaderService = testLoaderService;
        this.fileResolverService = fileResolverService;
    }

    public void process(String filename) throws TestInputException, IOException {
        List<QuestionModel> questions;

        try (BufferedReader reader = fileResolverService.getFileReader(filename)) {
            questions = questionsLoaderService.loadQuestions(reader);
        }

        outputService.printFioQuestion();
        String fio = inputService.getPersonFio();

        ReportModel report = new ReportModel();
        report.setFio(fio);
        report.setQuestionCount(questions.size());
        report.setPassed(true);

        for (QuestionModel question : questions) {
            outputService.printQuestion(question.getQuestionContent());
            String userAnswer = inputService.getUserAnswer(question.getQuestionContent());

            if (isCorrectAnswer(question, userAnswer)) {
                report.incSuccessAnswerCount();
                continue;
            }
            report.setPassed(false);
        }

        outputService.printTestReport(report);
    }

    private boolean isCorrectAnswer(QuestionModel question, String userAnswer) {
        return question.getCorrectAnswer().equals(userAnswer);
    }
}

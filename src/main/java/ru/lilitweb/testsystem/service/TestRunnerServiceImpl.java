package ru.lilitweb.testsystem.service;

import org.springframework.stereotype.Service;
import ru.lilitweb.testsystem.models.QuestionModel;
import ru.lilitweb.testsystem.models.ReportModel;

import java.io.IOException;
import java.util.List;

@Service
public class TestRunnerServiceImpl implements TestRunnerService {
    private TestInputService inputService;
    private TestOutputService outputService;
    private QuestionsLoaderService questionsLoaderService;

    public TestRunnerServiceImpl(
            TestInputService inputService,
            TestOutputService outputService,
            QuestionsLoaderService testLoaderService
    ) {
        this.inputService = inputService;
        this.outputService = outputService;
        this.questionsLoaderService = testLoaderService;
    }

    public void process() throws TestInputException, IOException {
        List<QuestionModel> questions = questionsLoaderService.loadQuestions();
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

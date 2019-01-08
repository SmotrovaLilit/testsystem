package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.Question;
import ru.lilitweb.testsystem.Report;

import java.util.List;
import java.util.Map;

public class TestServiceImpl implements TestService {
    private TestInputService inputService;
    private TestOutputService outputService;

    public TestServiceImpl(TestInputService inputService, TestOutputService outputService) {
        this.inputService = inputService;
        this.outputService = outputService;
    }

    public Report process(List<Question> questions) throws TestInputException {
        String fio = inputService.getPersonFio();
        Map<Question, String> answers = inputService.loadAnswers(questions);
        Report report = createReport(answers);
        report.setFio(fio);
        return report;
    }

    private Report createReport(Map<Question, String> answers) {
        Report report = new Report();
        report.setQuestionCount(answers.size());
        report.setPassed(true);
        for (Map.Entry<Question, String> entry : answers.entrySet()) {
            if (isCorrectAnswer(entry.getKey(), entry.getValue())) {
                report.incSuccessAnswerCount();
                continue;
            }
            report.setPassed(false);
        }

        return report;
    }

    private boolean isCorrectAnswer(Question q, String answer) {
        return q.getCorrectAnswer().equals(answer);
    }

    public void print(Report report) {
        outputService.print(report);
    }
}

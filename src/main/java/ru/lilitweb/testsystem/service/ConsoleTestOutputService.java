package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.ReportModel;

import java.io.PrintStream;

public class ConsoleTestOutputService implements TestOutputService {
    private PrintStream stream;
    private LocalisationService localisationService;

    public ConsoleTestOutputService(PrintStream stream, LocalisationService localisationService) {
        this.stream = stream;
        this.localisationService = localisationService;
    }

    public void printTestReport(ReportModel report) {
        stream.println(report.getFio() + ":");
        stream.println(String.format("%d/%d", report.getSuccessAnswerCount(), report.getQuestionCount()));
        if (report.isPassed()) {
            String mess = localisationService.getMessage("test.result.success");
            stream.println(mess);
            return;
        }
        stream.println(localisationService.getMessage("test.result.failed"));
    }

    @Override
    public void printQuestion(String question) {
        stream.println(question);
    }

    @Override
    public void printFioQuestion() {
        stream.println(localisationService.getMessage("input.name.label"));
    }
}

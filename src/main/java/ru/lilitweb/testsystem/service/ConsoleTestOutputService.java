package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.ReportModel;

import java.io.PrintStream;

public class ConsoleTestOutputService implements TestOutputService {
    private PrintStream stream;
    private LocalisationService source;

    public ConsoleTestOutputService(PrintStream stream, LocalisationService source) {
        this.stream = stream;
        this.source = source;
    }

    public void printTestReport(ReportModel report) {
        stream.println(report.getFio() + ":");
        stream.println(String.format("%d/%d", report.getSuccessAnswerCount(), report.getQuestionCount()));
        if (report.isPassed()) {
            String mess = source.getMessage("test.result.success", null);
            stream.println(mess);
            return;
        }
        stream.println(source.getMessage("test.result.failed", null));
    }

    @Override
    public void printQuestion(String question) {
        stream.println(question);
    }

    @Override
    public void printFioQuestion() {
        stream.println(source.getMessage("input.name.label", null));
    }
}

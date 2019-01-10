package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.Report;

import java.io.PrintStream;

public class ConsoleTestOutputService implements TestOutputService {
    private PrintStream stream;

    public ConsoleTestOutputService(PrintStream stream) {
        this.stream = stream;
    }

    public void print(Report report) {
        stream.println(report.getFio() + ":");
        stream.println(String.format("%d/%d", report.getSuccessAnswerCount(), report.getQuestionCount()));
        stream.println(report.isPassed() ? "Тест успешно пройден" : "Тест провален");
    }
}

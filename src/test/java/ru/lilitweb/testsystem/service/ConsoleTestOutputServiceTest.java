package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.lilitweb.testsystem.models.ReportModel;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConsoleTestOutputServiceTest {

    @Mock
    private LocalisationService source;

    @Mock
    private PrintStream stream;

    @BeforeEach
    void setUp() {
        source = mock(LocalisationService.class);
        stream = mock(PrintStream.class);
    }

    @Test
    void printTestReport() {
        TestOutputService service = new ConsoleTestOutputService(stream, source);
        ReportModel report = new ReportModel();
        report.setPassed(true);
        report.setQuestionCount(2);
        report.incSuccessAnswerCount();
        report.incSuccessAnswerCount();
        report.setFio("fio");
        when(source.getMessage("test.result.success", null)).thenReturn("ok");

        service.printTestReport(report);
        verify(stream).println("fio:");
        verify(stream).println("2/2");
        verify(stream).println("ok");
    }

    @Test
    void printTestReportFail() {
        TestOutputService service = new ConsoleTestOutputService(stream, source);
        ReportModel report = new ReportModel();
        report.setPassed(false);
        report.setQuestionCount(2);
        report.incSuccessAnswerCount();
        report.setFio("fio");
        when(source.getMessage("test.result.failed", null)).thenReturn("fail");

        service.printTestReport(report);
        verify(stream).println("fio:");
        verify(stream).println("1/2");
        verify(stream).println("fail");
    }

    // TODO: printQuestion
    // TODO: printFioQuestion
}

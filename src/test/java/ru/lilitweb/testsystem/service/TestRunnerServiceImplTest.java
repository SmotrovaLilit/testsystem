package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.lilitweb.testsystem.models.ReportModel;
import ru.lilitweb.testsystem.models.QuestionModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class TestRunnerServiceImplTest {
    private TestRunnerService testService;

    @Mock
    private TestOutputService outputService;

    @Mock
    private TestInputService inputService;

    @Mock
    private FileResolverService fileResolverService;

    @Mock
    private QuestionsLoaderService questionsLoaderService;

    @BeforeEach
    void setUp() {
        inputService = mock(TestInputService.class);
        outputService = mock(TestOutputService.class);
        questionsLoaderService = mock(QuestionsLoaderService.class);
        fileResolverService = mock(FileResolverService.class);
        testService = new TestRunnerServiceImpl(inputService, outputService, questionsLoaderService);

    }

    @Test
    void process() throws TestInputException, IOException {
        BufferedReader reader = new BufferedReader(new StringReader("question1;answer1;\n"));
        when(fileResolverService.getFileReader()).thenReturn(reader);

        List<QuestionModel> tests = new ArrayList<>();
        tests.add(new QuestionModel("question1", "answer1"));
        when(questionsLoaderService.loadQuestions()).thenReturn(tests);

        when(inputService.getPersonFio()).thenReturn("fio");
        when(inputService.getUserAnswer("question1")).thenReturn("answer1");

        testService.process();

        ReportModel report = new ReportModel();
        report.setFio("fio");
        report.setPassed(true);
        report.setQuestionCount(1);
        report.setSuccessAnswerCount(1);

        verify(outputService).printTestReport(report);
    }

    @Test
    void processFail() throws TestInputException, IOException {
        BufferedReader reader = new BufferedReader(new StringReader("question1;answer1;\n"));
        when(fileResolverService.getFileReader()).thenReturn(reader);

        List<QuestionModel> tests = new ArrayList<>();
        tests.add(new QuestionModel("question1", "answer1"));
        when(questionsLoaderService.loadQuestions()).thenReturn(tests);

        when(inputService.getPersonFio()).thenReturn("fio");
        when(inputService.getUserAnswer("question1")).thenReturn("wrong answer");

        testService.process();

        ReportModel report = new ReportModel();
        report.setFio("fio");
        report.setPassed(false);
        report.setQuestionCount(1);
        report.setSuccessAnswerCount(0);

        verify(outputService).printTestReport(report);
    }
}

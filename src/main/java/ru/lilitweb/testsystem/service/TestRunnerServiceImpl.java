package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.ReportModel;
import ru.lilitweb.testsystem.models.TestModel;

import java.io.*;
import java.util.List;

public class TestRunnerServiceImpl implements TestRunnerService {
    private TestInputService inputService;
    private TestOutputService outputService;
    private TestsLoaderService testLoaderService;
    private FileResolverService fileResolverService;

    public TestRunnerServiceImpl(
            TestInputService inputService,
            TestOutputService outputService,
            TestsLoaderService testLoaderService,
            FileResolverService fileResolverService
    ) {
        this.inputService = inputService;
        this.outputService = outputService;
        this.testLoaderService = testLoaderService;
        this.fileResolverService = fileResolverService;
    }

    public void process(String filename) throws TestInputException, IOException {
        List<TestModel> tests;

        try (BufferedReader reader = fileResolverService.getFileReader(filename)) {
            tests = testLoaderService.loadTests(reader);
        }

        String fio = inputService.getPersonFio();

        ReportModel report = new ReportModel();
        report.setFio(fio);
        report.setQuestionCount(tests.size());
        report.setPassed(true);

        for (TestModel test : tests) {
            test.setUserAnswer(inputService.getUserAnswer(test.getQuestionContent()));

            if (test.isCorrectAnswer()) {
                report.incSuccessAnswerCount();
                continue;
            }
            report.setPassed(false);
        }

        outputService.print(report);
    }
}

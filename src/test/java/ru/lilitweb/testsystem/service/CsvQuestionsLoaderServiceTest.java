package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.Test;
import ru.lilitweb.testsystem.models.TestModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvQuestionsLoaderServiceTest {

    @Test
    void getQuestions() throws IOException {
        TestsLoaderService service = new CsvTestsLoaderService();
        BufferedReader reader = new BufferedReader(new StringReader("question1;answer1\nquestion2;answer2\n"));
        List<TestModel> tests = service.loadTests(reader);

        assertEquals(tests.size(), 2);

        assertEquals(tests.get(0).getQuestionContent(), "question1");
        assertEquals(tests.get(0).getCorrectAnswer(), "answer1");

        assertEquals(tests.get(1).getQuestionContent(), "question2");
        assertEquals(tests.get(1).getCorrectAnswer(), "answer2");
    }
}

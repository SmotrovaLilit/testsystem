package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.lilitweb.testsystem.models.QuestionModel;
import ru.lilitweb.testsystem.service.CsvTestsLoaderService;
import ru.lilitweb.testsystem.service.FileResolverServiceImpl;
import ru.lilitweb.testsystem.service.QuestionsLoaderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CsvQuestionsLoaderServiceTest {

    @Mock
    private FileResolverServiceImpl fileResolverService;

    @BeforeEach
    void setUp() {
        fileResolverService = mock(FileResolverServiceImpl.class);
    }


    @Test
    void getQuestions() throws IOException {
        QuestionsLoaderService service = new CsvTestsLoaderService(fileResolverService);
        BufferedReader reader = new BufferedReader(new StringReader("question1;answer1\nquestion2;answer2\n"));
        when(fileResolverService.getFileReader()).thenReturn(reader);

        List<QuestionModel> tests = service.loadQuestions();

        assertEquals(tests.size(), 2);

        assertEquals(tests.get(0).getQuestionContent(), "question1");
        assertEquals(tests.get(0).getCorrectAnswer(), "answer1");

        assertEquals(tests.get(1).getQuestionContent(), "question2");
        assertEquals(tests.get(1).getCorrectAnswer(), "answer2");
    }
}

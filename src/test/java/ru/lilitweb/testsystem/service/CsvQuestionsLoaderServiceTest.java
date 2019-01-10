package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.Test;
import ru.lilitweb.testsystem.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvQuestionsLoaderServiceTest {

    @Test
    void getQuestions() throws IOException {
        QuestionsLoaderService service = new CsvQuestionsLoaderService();
        BufferedReader reader = new BufferedReader(new StringReader("question1;answer1\nquestion2;answer2\n"));
        List<Question> questions = service.getQuestions(reader);
        
        assertEquals(questions.size(), 2);

        assertEquals(questions.get(0).getQuestionContent(), "question1");
        assertEquals(questions.get(0).getCorrectAnswer(), "answer1");

        assertEquals(questions.get(1).getQuestionContent(), "question2");
        assertEquals(questions.get(1).getCorrectAnswer(), "answer2");
    }
}

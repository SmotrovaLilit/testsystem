package ru.lilitweb.testsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestResultModelTest {

    private List<QuestionModel> questions;

    @BeforeEach
    void setUp() throws IOException {
        questions = new ArrayList<>();
        questions.add(new QuestionModel("question1", "answer1"));
        questions.add(new QuestionModel("question2", "answer2"));
    }

    @Test
    void getSuccessAnswerCount() {
        TestResultModel testResultModel = new TestResultModel(questions, "fio");
        testResultModel.setQuestionAnswer(questions.get(0), "wrong");
        testResultModel.setQuestionAnswer(questions.get(1), "answer2");

        assertEquals(1, testResultModel.getSuccessAnswerCount());
    }

    @Test
    void getSuccessAnswerIfAllWrong() {
        TestResultModel testResultModel = new TestResultModel(questions, "fio");
        testResultModel.setQuestionAnswer(questions.get(0), "wrong");
        testResultModel.setQuestionAnswer(questions.get(1), "wrong2");

        assertEquals(0, testResultModel.getSuccessAnswerCount());
    }

    @Test
    void getSuccessAnswerIfAllSuccess() {
        TestResultModel testResultModel = new TestResultModel(questions, "fio");
        testResultModel.setQuestionAnswer(questions.get(0), "answer1");
        testResultModel.setQuestionAnswer(questions.get(1), "answer2");

        assertEquals(2, testResultModel.getSuccessAnswerCount());
    }

    @Test
    void isPassed() {
        TestResultModel testResultModel = new TestResultModel(questions, "fio");
        testResultModel.setQuestionAnswer(questions.get(0), "answer1");
        testResultModel.setQuestionAnswer(questions.get(1), "answer2");

        assertTrue(testResultModel.isPassed());
    }

    @Test
    void isPassedIfWrong() {
        TestResultModel testResultModel = new TestResultModel(questions, "fio");
        testResultModel.setQuestionAnswer(questions.get(0), "answer1");
        testResultModel.setQuestionAnswer(questions.get(1), "wrong");

        assertFalse(testResultModel.isPassed());
    }
}

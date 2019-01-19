package ru.lilitweb.testsystem.models;

import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestResultModel {
    private Map<QuestionModel, String> questionAnswers = new HashMap<>();
    @NonNull
    private String fio = "";

    public TestResultModel(List<QuestionModel> questions, String fio) {
        questions.forEach(question -> {
            questionAnswers.put(question, null);
        });

        this.fio = fio;
    }

    public void setQuestionAnswer(QuestionModel questionModel, String answer) {
        questionAnswers.put(questionModel, answer);
    }

    public int getSuccessAnswerCount() {
        return (int) questionAnswers.entrySet()
                .stream()
                .filter(entry -> {
                    return entry.getKey().getCorrectAnswer().equals(entry.getValue());
                })
                .count();
    }

    public int getQuestionsCount() {
        return questionAnswers.size();
    }

    public boolean isPassed() {
        return getSuccessAnswerCount() == questionAnswers.size();
    }

    public String getFio() {
        return fio;
    }
}

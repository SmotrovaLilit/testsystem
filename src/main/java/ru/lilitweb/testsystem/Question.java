package ru.lilitweb.testsystem;

public class Question {
    private String questionContent;
    private String correctAnswer;

    public Question(String questionContent, String correctAnswer) {
        this.questionContent = questionContent;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

package ru.lilitweb.testsystem.models;

public class Report {
    private int successAnswerCount;
    private int questionCount;
    private boolean isPassed;
    private String fio;

    public int getSuccessAnswerCount() {
        return successAnswerCount;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void incSuccessAnswerCount() {
        successAnswerCount++;
    }
}

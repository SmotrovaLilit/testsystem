package ru.lilitweb.testsystem.models;

import lombok.Data;

@Data
public class ReportModel {
    private int successAnswerCount;
    private int questionCount;
    private boolean isPassed;
    private String fio;

    public void incSuccessAnswerCount() {
        successAnswerCount++;
    }
}

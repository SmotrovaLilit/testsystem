package ru.lilitweb.testsystem.models;

import lombok.*;

@Data
@RequiredArgsConstructor
public class TestModel {
    @NonNull private String questionContent;
    @NonNull
    private String correctAnswer;
    private String userAnswer;

    public boolean isCorrectAnswer() {
        return correctAnswer.equals(userAnswer);
    }
}

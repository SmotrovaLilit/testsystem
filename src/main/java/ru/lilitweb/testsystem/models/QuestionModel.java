package ru.lilitweb.testsystem.models;

import lombok.*;

@Data
@RequiredArgsConstructor
public class QuestionModel {
    @NonNull private String questionContent;
    @NonNull private String correctAnswer;
}

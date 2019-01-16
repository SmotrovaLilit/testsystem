package ru.lilitweb.testsystem.models;

import lombok.*;
import org.springframework.lang.NonNull;

@Data
@RequiredArgsConstructor
public class QuestionModel {
    @NonNull
    private String questionContent;
    @NonNull private String correctAnswer;
}

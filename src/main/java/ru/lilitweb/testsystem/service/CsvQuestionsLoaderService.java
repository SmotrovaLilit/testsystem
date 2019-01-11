package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvQuestionsLoaderService implements QuestionsLoaderService {

    public List<Question> getQuestions(BufferedReader reader) throws IOException {
        ArrayList<Question> questions = new ArrayList<>();

        String separator = ";";
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(separator);
            if (data.length < 2) {
                continue;
            }
            questions.add(new Question(data[0], data[1]));
        }


        return questions;
    }
}

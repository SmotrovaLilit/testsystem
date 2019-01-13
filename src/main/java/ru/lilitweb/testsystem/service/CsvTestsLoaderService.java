package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.QuestionModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvTestsLoaderService implements QuestionsLoaderService {
    public List<QuestionModel> loadQuestions(BufferedReader reader) throws IOException {
        ArrayList<QuestionModel> tests = new ArrayList<>();

        String separator = ";";
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(separator);
            if (data.length < 2) {
                continue;
            }

            tests.add(new QuestionModel(data[0], data[1]));
        }

        return tests;
    }
}

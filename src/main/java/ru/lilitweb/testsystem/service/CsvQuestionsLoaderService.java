package ru.lilitweb.testsystem.service;

import org.springframework.util.ResourceUtils;
import ru.lilitweb.testsystem.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvQuestionsLoaderService implements QuestionsLoaderService{

    public List<Question> getQuestions() throws IOException {
        ArrayList<Question> questions = new ArrayList<>();

        String csvFile = "questions.csv";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(csvFile).getFile());

        String separator = ";";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                if (data.length < 2) {
                    continue;
                }
                questions.add(new Question(data[0], data[1]));
            }

        }

        return questions;
    }
}

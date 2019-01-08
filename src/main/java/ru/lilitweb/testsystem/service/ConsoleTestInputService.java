package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.Question;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleTestInputService implements TestInputService {
    private BufferedReader reader;

    public ConsoleTestInputService() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Map<Question, String> loadAnswers(List<Question> questions) throws TestInputException {
        Map<Question, String> res = new HashMap<>();
        try {
            for (Question q : questions) {
                System.out.println(q.getQuestionContent());
                String answer = reader.readLine();
                res.put(q, answer);
            }
        } catch (IOException e) {
            throw new TestInputException(e);
        }

        return res;
    }

    public String getPersonFio() throws TestInputException {
        System.out.println("Фамилия имя:");
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new TestInputException(e);
        }
    }
}

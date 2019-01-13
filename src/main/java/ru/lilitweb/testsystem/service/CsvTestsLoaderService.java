package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.TestModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvTestsLoaderService implements TestsLoaderService {
    public List<TestModel> loadTests(BufferedReader reader) throws IOException {
        ArrayList<TestModel> tests = new ArrayList<>();

        String separator = ";";
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(separator);
            if (data.length < 2) {
                continue;
            }

            tests.add(new TestModel(data[0], data[1]));
        }

        return tests;
    }
}

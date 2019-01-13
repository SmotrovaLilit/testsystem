package ru.lilitweb.testsystem.service;


import ru.lilitweb.testsystem.models.TestModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface TestsLoaderService {
    List<TestModel> loadTests(BufferedReader reader) throws IOException;
}

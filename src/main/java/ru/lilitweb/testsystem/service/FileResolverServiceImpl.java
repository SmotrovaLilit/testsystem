package ru.lilitweb.testsystem.service;

import org.springframework.stereotype.Service;
import ru.lilitweb.testsystem.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

@Service
public class FileResolverServiceImpl implements FileResolverService {
    @Override
    public BufferedReader getFileReader(String filename) throws FileNotFoundException {
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
        return new BufferedReader(new FileReader(file));
    }
}

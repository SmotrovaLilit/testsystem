package ru.lilitweb.testsystem.service;
import org.springframework.core.io.ResourceLoader;

import java.io.*;

public class FileResolverServiceImpl implements FileResolverService {
    private String filename;
    private ResourceLoader resourceLoader;

    public FileResolverServiceImpl(String filename, ResourceLoader resourceLoader) {
        this.filename = filename;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BufferedReader getFileReader() throws IOException {
        File file = new File(resourceLoader.getResource(filename).getURI());
        return new BufferedReader(new FileReader(file));
    }
}

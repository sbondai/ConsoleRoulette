package io;

import java.io.*;

public class FileLoader {
    public  FileInputStream readFile(String fileName) throws IOException {
        FileInputStream inputStream = null;


        ClassLoader classLoader = this.getClass().getClassLoader();

        File configFile = new File(classLoader.getResource(fileName).getFile());

        inputStream = new FileInputStream(configFile);
        return inputStream;
    }



}

package com.piotr1ulanowski.FIleReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** A class that reads through a given file. */
public class FileReader implements AutoCloseable {
    private File file;
    private Scanner scanner;

    public FileReader(String filePath) {
        try {
            file = new File(filePath);
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    public Boolean hasLine() {
        return scanner.hasNextLine();
    }

    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}

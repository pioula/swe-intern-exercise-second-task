package com.piotr1ulanowski.ServerTests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.piotr1ulanowski.Main;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTests {
    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream ERR_CONTENT = new ByteArrayOutputStream();
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final PrintStream ORIGINAL_ERR = System.err;
    private static final Gson gson = new Gson();

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
        System.setErr(new PrintStream(ERR_CONTENT));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
        System.setErr(ORIGINAL_ERR);
    }
    @DisplayName("Example tests")
    @Test
    void exampleTests() throws IOException {
        final String TEST_INPUT_PATH_PREFIX = "src/test/ex2/input";
        final String TEST_OUTPUT_PATH_PREFIX = "src/test/ex2/output";
        final String TEST_FILE_SUFFIX = ".txt";
        final String INPUT_FLAG = "-i";
        final int NUMBER_OF_TESTS = 1;

        for (int i = 1; i <= NUMBER_OF_TESTS; i++) {
            Main.main(new String[]{INPUT_FLAG, TEST_INPUT_PATH_PREFIX + i + TEST_FILE_SUFFIX});
            String correctOutput = new String(Files.readAllBytes(
                    Paths.get(TEST_OUTPUT_PATH_PREFIX + i + TEST_FILE_SUFFIX)));
            assertEquals(gson.fromJson(OUT_CONTENT.toString(), JsonObject.class),
                    gson.fromJson(correctOutput, JsonObject.class));
            OUT_CONTENT.reset();
        }
    }
}

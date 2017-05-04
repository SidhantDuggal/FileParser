package com.fileparsing.fileparser.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for handling files
 */

public class FileUtils {

    /**
     * Method that reads file contents line by line, appends it to a string and returns the string
     * at the end.
     */
    public static String readFileContent(File file) throws IOException{
        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            text.append(line);
            text.append('\n');
        }
        br.close();
        return text.toString();
    }
}

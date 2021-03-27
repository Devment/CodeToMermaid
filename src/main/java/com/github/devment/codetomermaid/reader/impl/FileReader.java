package com.github.devment.codetomermaid.reader.impl;

import com.github.devment.codetomermaid.config.Config;
import com.github.devment.codetomermaid.reader.intf.IFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @see IFileReader
 *
 * @author Devment
 */
public class FileReader implements IFileReader {
    /**
     * @see IFileReader#fileToString(Path)
     */
    @Override
    public String fileToString(Path file) {
        final StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file, Config.getInstance().getCharset())) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(Config.getInstance().getLineSeparator());
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x); //TODO replace with logger
        }
        return stringBuilder.toString();
    }
}

package com.github.devment.codetomermaid.reader.intf;

import java.nio.file.Path;

/**
 * Interface, for different file readers.
 *
 * @author Devment
 */
public interface IFileReader {
    /**
     * Reads a file and returns the contend as string.
     * @param file the {@link java.nio.file.Path} to the file.
     * @return the contend of the file.
     */
    String fileToString(Path file);
}

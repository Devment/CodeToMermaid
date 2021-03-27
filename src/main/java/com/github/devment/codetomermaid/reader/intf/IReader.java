package com.github.devment.codetomermaid.reader.intf;

import com.github.devment.codetomermaid.reader.File;
import com.github.devment.codetomermaid.reader.Project;

/**
 * Interface, for different reader parsing different programming languages.
 *
 * @author Devment
 */
public interface IReader {
    /**
     * Reads data from a directory and map the structure to the internal system.
     *
     * @param input the path to the source directory.
     * @return a {@link Project} that can be used to output the class diagram.
     */
    Project parseProject(String input);

    /**
     * Reads data from one file and map the structure to the internal system.
     *
     * @param input the path to the source file.
     * @return a {@link File} that can be used to output the class diagram.
     */
    File parseFile(String input);
}

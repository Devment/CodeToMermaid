package com.github.devment.codetomermaid.reader;

import com.github.devment.codetomermaid.config.Config;

/**
 * Data object storing all information regarding one <b>project</b>.
 */
public class Project {
    private final File[] files;

    /**
     * Constructor.
     * @param files all files in this project.
     */
    public Project(File[] files) {
        this.files = files;
    }

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder("classDiagram").
                append(Config.getInstance().getLineSeparator());
        for (File file: files) {
            stringBuilder.append(file.toString());
        }
        return stringBuilder.toString();
    }
}

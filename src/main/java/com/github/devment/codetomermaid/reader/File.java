package com.github.devment.codetomermaid.reader;

/**
 * Data object storing all information regarding one <b>file</b>.
 */
public class File {
    private final String name; //TODO not needed at the momentt
    private final Class[] classes;

    /**
     * Constructor.
     * @param name    name of the file.
     * @param classes in this file, also interfaces, enums, ...
     */
    public File(String name, Class[] classes) {
        this.name = name;
        this.classes = classes;
    }

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder();
        for (Class cl: classes) {
            stringBuilder.append(cl.toString());
        }
        return stringBuilder.toString();
    }
}

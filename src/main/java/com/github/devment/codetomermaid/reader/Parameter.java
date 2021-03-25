package com.github.devment.codetomermaid.reader;


/**
 * Data object storing all information regarding one <b>parameter</b>.
 */
public class Parameter {
    private final String name;
    private final String type;

    /**
     * Constructor.
     * @param name of the Parameter
     * @param type of the Parameter (e.g. String or int)
     */
    public Parameter(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * @return name of the parameter
     */
    public String getName(){
        return name;
    }

    /**
     * @return type of the parameter (e.g. String or int)
     */
    public String getType(){
        return type;
    }

    @Override
    public String toString(){
        return String.format("%s %s", type, name);
    }
}

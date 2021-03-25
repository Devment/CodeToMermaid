package com.github.devment.codetomermaid.reader;

/**
 * Data object storing all information regarding one <b>attribute</b>.
 */
public class Attribute extends Parameter {
    private final AccessModifier accessModifier;

    /**
     * Constructor.
     * @param name           of the attribute
     * @param type           of the attribute
     * @param accessModifier of the attribute (e.g. public or private)
     */
    public Attribute(String name,
                     String type,
                     AccessModifier accessModifier) {
        super(name, type);
        this.accessModifier = accessModifier;
    }

    /**
     * @return access modifier of the attribute (e.g. public(+) or private(-))
     */
    public AccessModifier getAccessModifier(){
        return accessModifier;
    }

    @Override
    public String toString(){
        return String.format("%s%s", accessModifier, super.toString());
    }
}

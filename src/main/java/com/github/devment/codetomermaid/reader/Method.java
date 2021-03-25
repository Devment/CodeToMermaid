package com.github.devment.codetomermaid.reader;

/**
 * Data object storing all information regarding one <b>method</b>.
 */
public class Method extends Attribute {
    private static final String EMPTYRETURNTYPE = "void";

    private final Parameter[] parameters;

    /**
     * Constructor.
     * @param name           of the method
     * @param returnType     of the method
     * @param accessModifier of the method (e.g. public or private)
     * @param parameters     handed to this method
     */
    public Method(String name,
                  String returnType,
                  AccessModifier accessModifier,
                  Parameter[] parameters) {
        super(name, returnType, accessModifier);
        this.parameters = parameters;
    }

    /**
     * Constructor.
     * @param name           of the method
     * @param returnType     of the method
     * @param accessModifier of the method (e.g. public or private)
     */
    public Method(String name,
                  String returnType,
                  AccessModifier accessModifier) {
        super(name, returnType, accessModifier);
        this.parameters = new Parameter[0];
    }

    /**
     * Constructor.
     * @param name           of the method
     * @param accessModifier of the method (e.g. public or private)
     * @param parameters     handed to this method
     */
    public Method(String name,
                  AccessModifier accessModifier,
                  Parameter[] parameters) {
        super(name, EMPTYRETURNTYPE, accessModifier);
        this.parameters = parameters;

    }

    /**
     * Constructor.
     * @param name           of the method
     * @param accessModifier of the method (e.g. public or private)
     */
    public Method(String name,
                  AccessModifier accessModifier) {
        super(name, EMPTYRETURNTYPE, accessModifier);
        this.parameters = new Parameter[0];
    }

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder(
                getAccessModifier().toString()).append(getName()).append('(');
        boolean first = true;
        for (Parameter parameter: parameters) {
            if(first)
                first = false;
            else
                stringBuilder.append(", ");
            stringBuilder.append(parameter.toString());
        }
        stringBuilder.append(") ").append(getType());
        return stringBuilder.toString();
    }
}

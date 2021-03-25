package com.github.devment.codetomermaid.reader;

import com.github.devment.codetomermaid.config.Config;

/**
 * Data object storing all information regarding one <b>class</b>.
 */
public class Class {
    private final String name;
    private final Attribute[] attributes;
    private final Method[] methods;
    //TODO add AccessModifier -> not yet possible in mermaid (see #2)

    /**
     * Constructor.
     * @param name       of the class
     * @param attributes of this class ({@link Attribute})
     * @param methods    of this class ({@link Method})
     */
    public Class(String name, Attribute[] attributes, Method[] methods) {
        this.name = name;
        this.attributes = attributes;
        this.methods = methods;
    }

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder("class ").append(name)
                .append('{').append(Config.getInstance().getLineSeparator());
        for (Attribute attribute : attributes) {
            stringBuilder.append(attribute.toString()).append(Config.getInstance().getLineSeparator());
        }
        for (Method method : methods) {
            stringBuilder.append(method.toString()).append(Config.getInstance().getLineSeparator());
        }
        return stringBuilder.append('}').append(Config.getInstance().getLineSeparator()).toString();
    }
}

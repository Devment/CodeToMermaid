package com.github.devment.codetomermaid.reader;

/**
 * Enum representing access modifier and storing the information of the corresponding symbol.
 * <a href="http://mermaid-js.github.io/mermaid/#/classDiagram?id=visibility">Doku</a>
 */
public enum AccessModifier {
    INTERNAL('~'),
    PACKAGE('~'),
    PRIVATE('-'),
    PROTECTED('#'),
    PUBLIC('+');

    private final char symbol;

    AccessModifier(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}

package com.github.devment.codetomermaid.reader


import spock.lang.Specification

class BasicTypesSpec extends Specification {
    def "Test toString methods and Object creation" (){
        given:

        Attribute[] attributes = new Attribute[]{
            new Attribute("attribute1", "String", AccessModifier.PRIVATE),
            new Attribute("attribute2", "int", AccessModifier.INTERNAL),
            new Attribute("attribute3", "Class1", AccessModifier.PUBLIC),
        };
        Method[] methods = new Method[]{
            new Method("getSomething", "String", AccessModifier.PUBLIC),
            new Method("setSomething", AccessModifier.PRIVATE, new Parameter[]{
                    new Parameter("something", "String")
            })
        };
        Class[] classes1 = new Class[]{
            new Class("Class1",attributes, methods)
        };
        Class[] classes2 = new Class[]{
                new Class("Class2",attributes, methods)
        };
        File fileA = new File("FileA", classes1);
        File fileB = new File("FileB", classes2);
        File[] files = new File[] {fileA, fileB};
        Project project = new Project(files);

        when:
        String result = project.toString();

        then:
        assert result == "classDiagram\r\nclass Class1{\r\n-String attribute1\r\n~int attribute2\r\n+Class1 attribute3\r\n+getSomething() String\r\n-setSomething(String something) void\r\n}\r\nclass Class2{\r\n-String attribute1\r\n~int attribute2\r\n+Class1 attribute3\r\n+getSomething() String\r\n-setSomething(String something) void\r\n}\r\n";

    }

}


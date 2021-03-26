package com.github.devment.codetomermaid.reader

import com.github.devment.codetomermaid.reader.impl.FileReader
import com.github.devment.codetomermaid.reader.impl.JavaReader
import com.github.devment.codetomermaid.reader.intf.IFileReader
import com.github.devment.codetomermaid.reader.intf.IReader
import spock.lang.Specification

import java.nio.file.Path
import java.nio.file.Paths

/**
 * All Tests regarding the {@link IFileReader} and {@link IReader} implementations.
 *
 * @author Devment
 */
class ReaderSpec extends Specification {
    def "Test FileReader implementation" (){
        IFileReader fileReader = new FileReader();
        Path testFile = Paths.get(this.class.getClassLoader().getResource("txt/FileReaderExample.txt").toURI()).normalize();

        when:
        def result = fileReader.fileToString(testFile);

        then:
        result == "test file the file reader test in 'ReaderSpec'\r\n" +
                  "\r\n" +
                  "\\r\\n @%& &nbsp; test text" +
                  "\r\n";
    }

    def "Test JavaReader implementation for one file" () {
        IReader reader = new JavaReader();

        when:
        def result = reader.parseFile(getExampleData()).toString();

        then:
        result ==
                "class JavaExampleClass{\r\n" +
                "-int privateAttribute\r\n" +
                "+String publicAttribute\r\n" +
                "#byte multipleAttribute1\r\n" +
                "#byte multipleAttribute2\r\n" +
                "+Object attribute\r\n" +
                "+getPrivateAttribute() int\r\n" +
                "+setPrivateAttribute() void\r\n" +
                "}\r\n" +
                "class JavaExampleInterface{\r\n" +
                "~getPrivateAttribute() int\r\n" +
                "~setPrivateAttribute() void\r\n" +
                "}\r\n"

    }

    String exampleData = ""

    String getExampleData(){
        if (!exampleData.isEmpty()) return exampleData;
        IFileReader fileReader = new FileReader();
        Path testFile = Paths.get(this.class.getClassLoader().getResource("java/JavaExampleClass.java").toURI()).normalize();

        exampleData = fileReader.fileToString(testFile);
        return exampleData;
    }

}
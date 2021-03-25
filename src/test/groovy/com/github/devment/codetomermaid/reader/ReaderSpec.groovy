package com.github.devment.codetomermaid.reader

import com.github.devment.codetomermaid.reader.impl.FileReader
import com.github.devment.codetomermaid.reader.intf.IFileReader
import spock.lang.Specification

import java.nio.file.Path
import java.nio.file.Paths


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

}
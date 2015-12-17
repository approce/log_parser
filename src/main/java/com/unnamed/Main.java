package com.unnamed;

import com.unnamed.file.FileReader;
import com.unnamed.file.XmlWriter;
import com.unnamed.model.Rendering;
import com.unnamed.model.Summary;
import com.unnamed.model.Wrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException, JAXBException {
        String filePath = args[0];
        FileReader fileReader = new FileReader();
        LogProcessor logProcessor = new LogProcessor();
        SummaryBuilder summaryBuilder = new SummaryBuilder();
        XmlWriter xmlWriter = new XmlWriter();


        Stream<String> linesStream = fileReader.read(Paths.get(filePath));

        List<Rendering> renderings = logProcessor.process(linesStream);

        Summary summary = summaryBuilder.build(renderings);

        xmlWriter.write(new Wrapper(renderings, summary), filePath.substring(0, filePath.lastIndexOf("/")));

        System.out.println("Successfully finished");
    }
}
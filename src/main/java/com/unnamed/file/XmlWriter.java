package com.unnamed.file;

import com.unnamed.model.Wrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlWriter {

    private static final String RESULT_FILENAME = "/result.xml";

    public void write(Wrapper wrapper, String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Wrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(wrapper, new File(path + RESULT_FILENAME));
    }
}
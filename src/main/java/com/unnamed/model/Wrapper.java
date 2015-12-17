package com.unnamed.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Wrapper {
    public Summary summary;

    @XmlElement(name = "rendering")
    public List<Rendering> renderingList;

    public Wrapper() {
    }

    public Wrapper(List<Rendering> renderingList, Summary summary) {
        this.renderingList = renderingList;
        this.summary = summary;
    }
}

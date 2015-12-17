package com.unnamed.model;

import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Rendering {
    public String threadName;
    public long   documentId;
    public long   page;
    public String uid;

    @XmlTransient
    public boolean expired;

    public List<LocalDateTime> starts = new ArrayList<>();
    public List<LocalDateTime> gets   = new ArrayList<>();

    public Rendering() {
    }

    public Rendering(long documentId, String threadName) {
        this.documentId = documentId;
        this.threadName = threadName;
    }

    @Override
    public String toString() {
        return "Rendering{" +
               "threadName='" + threadName + '\'' +
               ", documentId=" + documentId +
               ", page=" + page +
               ", uid='" + uid + '\'' +
               ", expired=" + expired +
               ", \n\tstarts=" + starts +
               ", \n\tgets=" + gets +
               "\n\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rendering rendering = (Rendering) o;

        return documentId == rendering.documentId;

    }

    @Override
    public int hashCode() {
        return (int) (documentId ^ (documentId >>> 32));
    }
}
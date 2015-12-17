package com.unnamed.extractors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.regex.Pattern.compile;

public class Extractors {
    public static class DateExtractor extends Extractor {
        public DateExtractor() {
            super(compile("\\d{4}\\-\\d{2}\\-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}"));
        }

        public LocalDateTime extractDate(String value) {
            String extractedString = extract(value);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
            return LocalDateTime.parse(extractedString, formatter);
        }
    }

    public static class DocumentIdExtractor extends Extractor {
        public DocumentIdExtractor() {
            super(compile("\\[\\d++"), s -> s.substring(1));
        }

        public Long extractLong(String value) {
            return Long.parseLong(extract(value));
        }
    }

    public static class RenderingIdExtractor extends Extractor {
        public RenderingIdExtractor() {
            super(compile("\\d{5,}\\-\\d++"), s -> s.substring(1));
        }
    }

    public static class ThreadNameExtractor extends Extractor {
        public ThreadNameExtractor() {
            super(compile("\\d{3} \\S+"), s -> s.substring(5, s.length() - 1));
        }
    }
}
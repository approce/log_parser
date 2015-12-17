package com.unnamed.extractors;

import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {

    private Pattern               pattern;
    private UnaryOperator<String> postProcessor;

    public Extractor(Pattern pattern) {
        this.pattern = pattern;
    }

    public Extractor(Pattern pattern, UnaryOperator<String> postProcessor) {
        this.pattern = pattern;
        this.postProcessor = postProcessor;
    }

    public String extract(String text) {
        Matcher matcher = pattern.matcher(text);

        findAndValidate(matcher, text);

        String extractedValue = matcher.group();
        extractedValue = postProcessIfNeeded(extractedValue);

        return extractedValue;
    }

    private void findAndValidate(Matcher matcher, String text) {
        if (!matcher.find()) {
            throw new IllegalArgumentException(
                    String.format("There is no matched value for Pattern: {%s} in String: {%s}", pattern, text));
        }
    }

    private String postProcessIfNeeded(String extractedValue) {
        if (postProcessor != null) {
            extractedValue = postProcessor.apply(extractedValue);
        }
        return extractedValue;
    }
}
package com.unnamed.messageProcessor;

import com.unnamed.model.Rendering;

import java.util.List;
import java.util.function.BiConsumer;

import static com.unnamed.extractors.Extractors.DateExtractor;
import static com.unnamed.extractors.Extractors.DocumentIdExtractor;
import static com.unnamed.extractors.Extractors.RenderingIdExtractor;
import static com.unnamed.extractors.Extractors.ThreadNameExtractor;

public abstract class MessageProcessor implements BiConsumer<List<Rendering>, String> {

    protected DocumentIdExtractor  documentIdExtractor  = new DocumentIdExtractor();
    protected RenderingIdExtractor renderingIdExtractor = new RenderingIdExtractor();
    protected ThreadNameExtractor  threadNameExtractor  = new ThreadNameExtractor();
    protected DateExtractor        dateExtractor        = new DateExtractor();


    /**
     * Validate's if message can can be processed by MessageProcessor
     *
     * @param s String value
     * @return true is MessageProcessor can process input value
     */
    public abstract boolean check(String s);
}
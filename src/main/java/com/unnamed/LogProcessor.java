package com.unnamed;

import com.unnamed.messageProcessor.MessageProcessor;
import com.unnamed.messageProcessor.RequestGet;
import com.unnamed.messageProcessor.RequestStart;
import com.unnamed.messageProcessor.UidGenerated;
import com.unnamed.model.Rendering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LogProcessor {

    private List<MessageProcessor> messageProcessors =
            Arrays.asList(new RequestStart(), new RequestGet(), new UidGenerated());

    public List<Rendering> process(Stream<String> stream) {
        ArrayList<Rendering> renderings = new ArrayList<>();

        stream.forEach(s -> processLine(renderings, s));

        return renderings;
    }

    private void processLine(List<Rendering> renderingList, String s) {
        messageProcessors.stream().filter(mp -> mp.check(s)).findFirst().ifPresent(mp -> mp.accept(renderingList, s));
    }

}
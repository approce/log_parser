package com.unnamed.messageProcessor;

import com.unnamed.Marker;
import com.unnamed.model.Rendering;

import java.util.List;

public class RequestStart extends MessageProcessor {

    @Override
    public boolean check(String s) {
        return s.contains(Marker.REQUEST_START);
    }

    @Override
    public void accept(List<Rendering> list, String line) {
        String threadName = threadNameExtractor.extract(line);
        Long documentId = documentIdExtractor.extractLong(line);

        expireRenderingWithSameThreadNameIfNeeded(list, threadName);

        list.add(new Rendering(documentId, threadName));
    }

    private void expireRenderingWithSameThreadNameIfNeeded(List<Rendering> renderingList, String threadName) {
        renderingList.stream()
                     .filter(rendering -> threadName.equals(rendering.threadName) && !rendering.expired)
                     .findFirst()
                     .ifPresent(rendering1 -> rendering1.expired = true);
    }
}
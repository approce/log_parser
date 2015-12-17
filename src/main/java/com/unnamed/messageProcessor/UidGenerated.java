package com.unnamed.messageProcessor;

import com.unnamed.Marker;
import com.unnamed.model.Rendering;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UidGenerated extends MessageProcessor {

    @Override
    public boolean check(String s) {
        return s.contains(Marker.UID_GENERATED);
    }

    @Override
    public void accept(List<Rendering> list, String line) {
        String threadName = threadNameExtractor.extract(line);
        Optional<Rendering> properRendering = findLastRenderingByThreadName(list, threadName);

        if (properRendering.isPresent()) {
            Rendering rendering = properRendering.get();
            String generatedUid = renderingIdExtractor.extract(line);
            LocalDateTime date = dateExtractor.extractDate(line);

            rendering.uid = generatedUid;
            rendering.starts.add(date);
        } else {
            //Returned uid for not existing file.
        }
    }

    private Optional<Rendering> findLastRenderingByThreadName(List<Rendering> renderingList, String threadName) {
        return renderingList.stream()
                            .filter(rendering -> rendering.threadName.equals(threadName) && !rendering.expired)
                            .findFirst();
    }
}
package com.unnamed.messageProcessor;

import com.unnamed.model.Rendering;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.unnamed.Marker.REQUEST_GET;

public class RequestGet extends MessageProcessor {
    @Override
    public boolean check(String s) {
        return s.contains(REQUEST_GET);
    }

    @Override
    public void accept(List<Rendering> renderingList, String line) {
        String uid = renderingIdExtractor.extract(line);
        Optional<Rendering> optional = findRenderingByUid(renderingList, uid);

        if (optional.isPresent()) {
            Rendering rendering = optional.get();
            LocalDateTime date = dateExtractor.extractDate(line);

            rendering.gets.add(date);
        } else {
            //"Get" request for not existing file
        }
    }

    private Optional<Rendering> findRenderingByUid(List<Rendering> renderingList, String uid) {
        return renderingList.stream().filter(rendering -> uid.equals(rendering.uid) && !rendering.expired).findFirst();
    }
}

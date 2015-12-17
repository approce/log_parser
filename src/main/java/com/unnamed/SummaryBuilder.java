package com.unnamed;

import com.unnamed.model.Rendering;
import com.unnamed.model.Summary;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SummaryBuilder {

    public Summary build(List<Rendering> renderingList) {
        Summary summary = new Summary();
        summary.count = getTotalCount(renderingList);
        summary.duplicates = getDuplicationCount(renderingList);
        summary.unnecessary = getUnnecessaryCount(renderingList);
        return summary;
    }

    private long getTotalCount(List<Rendering> renderingList) {
        return renderingList.size();
    }

    private int getDuplicationCount(List<Rendering> renderingList) {
        HashMap<Long, Integer> duplications = new HashMap<>();
        renderingList.stream().filter(r -> Collections.frequency(renderingList, r) > 1).forEach(r -> {
            duplications.put(r.documentId, Collections.frequency(renderingList, r));
        });
        return duplications.values().stream().mapToInt(value -> value).sum();
    }

    private long getUnnecessaryCount(List<Rendering> renderingList) {
        return renderingList.stream().filter(r -> r.gets.isEmpty()).count();
    }
}
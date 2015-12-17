package com.unnamed.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.unnamed.Marker.REQUEST_GET;
import static com.unnamed.Marker.REQUEST_START;
import static com.unnamed.Marker.UID_GENERATED;

public class FileReader {

    public Stream<String> read(Path path) throws IOException {
        return Files.lines(path).filter(filterRedundancy());
    }

    public Predicate<String> filterRedundancy() {
        return s -> s.contains(REQUEST_GET) || s.contains(REQUEST_START) || s.contains(UID_GENERATED);
    }
}
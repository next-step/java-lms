package nextstep.courses.utils;

import nextstep.courses.domain.SessionCover;

import java.io.File;
import java.io.IOException;

@FunctionalInterface
public interface ImageProcessor {
    SessionCover processImage(File file) throws IOException;
}

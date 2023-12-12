package nextstep.courses.utils;

import nextstep.courses.domain.SessionCover;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SessionCoverLoader {
    public static final List<String> EXTENSIONS = Arrays.asList("gif", "jpg", "jpeg", "png", "svg");

    public static SessionCover loadImage(String path, ImageProcessor imageProcessor) throws IOException {
        validateNull(path);
        validateExtension(path);
        return readImage(path, imageProcessor);
    }

    private static SessionCover readImage(String path, ImageProcessor imageProcessor) throws IOException {
        return imageProcessor.processImage(new File(path));
    }

    private static void validateNull(String path) {
        if (path == null) {
            throw new IllegalArgumentException("커버이미지 파일 경로를 입력해주세요.");
        }
    }

    private static void validateExtension(String path) {
        String fileExtension = path.substring(path.lastIndexOf('.') + 1).toLowerCase();
        if (!EXTENSIONS.contains(fileExtension)) {
            throw new IllegalArgumentException("지원하지 않는 이미지 타입입니다.");
        }
    }

}

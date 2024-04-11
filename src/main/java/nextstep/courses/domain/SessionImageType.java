package nextstep.courses.domain;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public enum SessionImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static SessionImageType findByFilePath(String filePath) {
        String invalidFiletypeMessage = "올바르지 않은 확장자입니다.";

        String extension = getExtension(filePath);

        return Arrays.stream(SessionImageType.values())
                .filter(sessionImageType -> sessionImageType.name().equalsIgnoreCase(extension))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(invalidFiletypeMessage));
    }

    private static String getExtension(String filePath) {
        String invalidFilePathMessage = "올바르지 않은 파일 경로입니다.";

        return Optional.ofNullable(filePath)
                .filter(path -> path.contains("."))
                .map(path -> path.substring(filePath.lastIndexOf(".") + 1))
                .orElseThrow(() -> new IllegalArgumentException(invalidFilePathMessage));
    }
}

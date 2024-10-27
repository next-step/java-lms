package nextstep.session.image;

import java.util.Arrays;

public enum ImageExtension {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private static final String NOT_APPLY_EXTENSION_MESSAGE = "유효하지 않은 이미지 확장자입니다.";
    private static final String NOT_EXIST_EXTENSION_MESSAGE = "이미지 확장자가 존재하지 않습니다.";

    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

    public static void confirmImageExtension(String fileName) {
        String fileExtension = getFileExtension(fileName);
        boolean isValid = Arrays.stream(values())
                .anyMatch(imageExtension -> imageExtension.extension.equalsIgnoreCase(fileExtension));

        if (!isValid) {
            throw new IllegalArgumentException(NOT_APPLY_EXTENSION_MESSAGE);
        }
    }

    private static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex != -1 && lastIndex < fileName.length() - 1) {
            return fileName.substring(lastIndex + 1);
        }
        throw new IllegalArgumentException(NOT_EXIST_EXTENSION_MESSAGE);
    }
}

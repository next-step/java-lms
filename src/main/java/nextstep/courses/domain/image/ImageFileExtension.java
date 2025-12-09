package nextstep.courses.domain.image;

import nextstep.courses.InvalidImageFileException;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ImageFileExtension {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String value;

    ImageFileExtension(String value) {
        this.value = value;
    }

    private String value() {
        return value;
    }

    static ImageFileExtension from(String value) {
        return Arrays.stream(ImageFileExtension.values())
                .filter(fileExtension -> fileExtension.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new InvalidImageFileException(String.format("이미지 파일은 %s 중에 하나의 형태여야 합니다.", possibleFileExtensions())));
    }

    private static String possibleFileExtensions() {
        return Arrays.stream(ImageFileExtension.values())
                .map(ImageFileExtension::value)
                .collect(Collectors.joining(", "));
    }
}

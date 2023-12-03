package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {
    gif("gif"),
    jpg("jpg"),
    jpeg("jpeg"),
    png("png"),
    svg("svg");

    private String imageType;

    ImageType(String imageType) {
        this.imageType = imageType;
    }

    public static boolean isValidType(String imageType) {
        return Arrays.stream(values()).noneMatch(type -> type.name().equals(imageType));
    }
}

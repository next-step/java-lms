package nextstep.courses.constant;

import java.util.Arrays;

public enum ImageFileType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String fileType;

    ImageFileType(String fileType) {
        this.fileType = fileType;
    }

    public static ImageFileType getImageFileType(String input) {
        return Arrays.stream(ImageFileType.values()).filter(type -> type.fileType.equals(input)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(input + "은 유효한 이미지 파일이 아닙니다. (허용 타입: gif, jpg, jpeg, png, svg)"));
    }
}

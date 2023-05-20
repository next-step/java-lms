package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum ImageType {
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif");

    private String value;

    ImageType(String value) {
        this.value = value;
    }

    public String getMimeType() {
        return this.value;
    }

    public static boolean canUpload(ImageType type) {
        return Arrays.stream(values())
                .anyMatch(validType -> validType == type);
    }
}

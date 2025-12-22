package nextstep.sessions.domain.image;

import java.util.Arrays;

public enum ImageType {
    GIF, JPG, JPEG, PNG, SVG;

    private static final String ERROR_EMPTY_EXT = "확장자가 유효하지 않습니다";
    private static final String ERROR_UNSUPPORTED_EXT = "지원하지 않는 이미지 형식입니다: ";

    public static ImageType from(String ext) {
        if (ext == null || ext.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_EXT);
        }

        return Arrays.stream(ImageType.values())
                .filter(type -> type.name().equalsIgnoreCase(ext))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_UNSUPPORTED_EXT + ext));
    }
}


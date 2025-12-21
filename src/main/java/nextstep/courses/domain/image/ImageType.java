package nextstep.courses.domain.image;

import java.util.Arrays;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static ImageType extractType(String fileName) {
        int extensionIndex = fileName.lastIndexOf(".");
        if (extensionIndex == -1) {
            throw new RuntimeException("확장자가 존재하지 않는 파일입니다.");
        }

        String type = fileName.substring(extensionIndex + 1);
        return findType(type);
    }

    private static ImageType findType(String type) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("지원하지 않는 이미지 타입입니다."));
    }
}

package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionCoverImageExtension {
    GIF, JPG, JPEG, PNG, SVG;

    public static SessionCoverImageExtension from(String input) {
        return Arrays.stream(SessionCoverImageExtension.values())
                .filter(extension -> extension.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 확장자입니다 : " + input));
    }
}

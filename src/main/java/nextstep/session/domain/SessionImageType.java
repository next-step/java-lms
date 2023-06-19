package nextstep.session.domain;

import java.util.Arrays;

public enum SessionImageType {
    JPEG, JPG, PNG, GIF;

    public static SessionImageType of(String urlString) {
        return Arrays.stream(values())
                .filter(type -> urlString.toUpperCase().contains(type.name().toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 이미지 형식입니다"));
    }
}

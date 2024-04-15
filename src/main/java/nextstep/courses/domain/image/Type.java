package nextstep.courses.domain.image;

import java.util.Arrays;

public enum Type {
    GIF("gif"), JPG("jpg"), JPEG("jpeg"), PNG("png"), SVG("svg");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    static Type find(String value) {
        return Arrays.stream(Type.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getValue() {
        return value;
    }
}

package nextstep.courses.domain.course.image;

import java.util.Arrays;

public enum Type {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    Type(String description) {
        this.description = description;
    }

    private final String description;

    public static Type find(String name) {
        return Arrays.stream(values())
                .filter(imageType -> imageType.description.equals(name))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                String.format("허용하는 확장자는 다음과 같습니다.\n %s", descriptions())
                        )
                );
    }

    public static String descriptions() {
        StringBuilder sb = new StringBuilder();
        for (Type type : values()) {
            sb.append(type.description).append(", ");
        }
        sb.setLength(sb.length() - 2);

        return sb.toString();
    }
}

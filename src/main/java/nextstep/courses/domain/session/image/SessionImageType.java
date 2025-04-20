package nextstep.courses.domain.session.image;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum SessionImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String type;

    SessionImageType(String type) {
        this.type = type;
    }

    public static SessionImageType fromString(String image) {
        return Stream.of(SessionImageType.values())
            .filter(type -> type.type.equalsIgnoreCase(image))
            .findFirst()
            .orElse(null);
    }
}

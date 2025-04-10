package nextstep.courses.domain.session;

import java.util.Arrays;

public enum SessionImageType {
    gif("gif"), jpg("jpg"), jpeg("jpeg"), png("png"), svg("svg");

    private final String name;

    SessionImageType(String name) {
        this.name = name;
    }

    public static SessionImageType of(String name) {
        return Arrays.stream(values())
                .filter(type -> type.name.equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}

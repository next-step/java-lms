package nextstep.users.domain;

import java.util.Arrays;

public enum Type {
    TEACHER("강사"), STUDENT("학생");

    private final String description;

    Type(String description) {
        this.description = description;
    }

    public static Type find(String name) {
        return Arrays.stream(values())
                .filter(status -> status.name().equals(name))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                String.format("허용하는 값은 다음과 같습니다.\n %s", descriptions())
                        )
                );
    }

    public boolean isTeacher() {
        return this == TEACHER;
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

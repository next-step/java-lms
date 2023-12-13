package nextstep.courses.domain.course.session;

import java.util.Arrays;

public enum RecruitStatus {
    RECRUIT("모집중"),
    NOT_RECRUIT("비모집중");

    private final String description;

    RecruitStatus(String description) {
        this.description = description;
    }

    public static RecruitStatus find(String name) {
        return Arrays.stream(values())
                .filter(status -> status.name().equals(name))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                String.format("허용하는 값은 다음과 같습니다.\n %s", descriptions())
                        )
                );
    }

    public static String descriptions() {
        StringBuilder sb = new StringBuilder();
        for (RecruitStatus recruitStatus : values()) {
            sb.append(recruitStatus.description).append(", ");
        }
        sb.setLength(sb.length() - 2);

        return sb.toString();
    }
}

package nextstep.courses.domain.course.session;

import java.util.Arrays;

public enum SessionRecruitStatus {
    RECRUIT("모집중"),
    NOT_RECRUIT("비모집중");

    private final String description;

    SessionRecruitStatus(String description) {
        this.description = description;
    }

    public static SessionRecruitStatus find(String name) {
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
        for (SessionRecruitStatus sessionRecruitStatus : values()) {
            sb.append(sessionRecruitStatus.description).append(", ");
        }
        sb.setLength(sb.length() - 2);

        return sb.toString();
    }

    public boolean notRecruiting() {
        return this == SessionRecruitStatus.NOT_RECRUIT;
    }
}

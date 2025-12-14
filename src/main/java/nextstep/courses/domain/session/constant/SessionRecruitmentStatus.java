package nextstep.courses.domain.session.constant;

import java.util.Arrays;

public enum SessionRecruitmentStatus {

    NOT_RECRUITING("비모집중"),
    RECRUITING("모집중");

    private String status;

    SessionRecruitmentStatus(String status) {
        this.status = status;
    }


    public static SessionRecruitmentStatus from(String value) {
        return Arrays.stream(SessionRecruitmentStatus.values())
                .filter(status -> status.matchStatus(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("모집중, 비모집중 2가지 상태로만 생성 가능합니다."));
    }

    private boolean matchStatus(String value) {
        return this.name().equals(value);
    }

}

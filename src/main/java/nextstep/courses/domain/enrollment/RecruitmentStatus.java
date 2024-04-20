package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionRecruitmentStatusInvalidException;

import java.util.Arrays;

public enum RecruitmentStatus {

    RECRUITMENT("모집중", true),
    NOT_RECRUITMENT("비모집중", false)
    ;

    private final String status;

    private final boolean canEnroll;

    RecruitmentStatus(String status, boolean canEnroll) {
        this.status = status;
        this.canEnroll = canEnroll;
    }

    public boolean cannotEnroll() {
        return !canEnroll;
    }

    public static RecruitmentStatus convert(String status) {
        return Arrays.stream(values())
                .filter(recruitmentStatus -> recruitmentStatus.status.equals(status))
                .findAny()
                .orElseThrow(() -> new SessionRecruitmentStatusInvalidException(status));
    }

    public String get() {
        return status;
    }

}

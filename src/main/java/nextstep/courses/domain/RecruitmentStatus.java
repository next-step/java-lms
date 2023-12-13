package nextstep.courses.domain;

import nextstep.courses.exception.InvalidRecruitmentStatusException;

import java.util.Arrays;

public enum RecruitmentStatus {
    NOT_RECRUITMENT,
    RECRUITING;

    private static final RecruitmentStatus[] VALUES = values();

    public static RecruitmentStatus findByName(String name) {
        return Arrays.stream(VALUES)
                .filter(status -> status.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new InvalidRecruitmentStatusException(name));
    }

    public RecruitmentStatus ofRecruiting() {
        return RECRUITING;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}

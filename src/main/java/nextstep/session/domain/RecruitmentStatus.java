package nextstep.session.domain;

import nextstep.session.NotFoundStatusException;

import java.util.Arrays;

public enum RecruitmentStatus {
    RECRUITING("recruiting"),
    NOT_RECRUITING("not_recruiting");

    private String status;

    RecruitmentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static RecruitmentStatus of(String status) throws NotFoundStatusException {
        return Arrays.stream(values())
                .filter(value -> value.status.equals(status))
                .findAny()
                .orElseThrow(() -> new NotFoundStatusException("상태가 존재하지 않습니다."));
    }
}

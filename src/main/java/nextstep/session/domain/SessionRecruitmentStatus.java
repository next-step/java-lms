package nextstep.session.domain;

import java.util.Arrays;

public enum SessionRecruitmentStatus {

    RECRUIT("모집중"),
    NON_RECRUIT("비모집중");

    private final String recruitmentStatus;

    SessionRecruitmentStatus(String recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public static SessionRecruitmentStatus of(String status) {
        return Arrays.stream(values()).filter(s -> status.equals(s.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 모집 상태입니다."));
    }

    public boolean isRecruitable() {
        return this.equals(RECRUIT);
    }
}

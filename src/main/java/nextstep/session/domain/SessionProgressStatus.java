package nextstep.session.domain;

import java.util.Arrays;

public enum SessionProgressStatus {

    PREPARING("준비중"),
    PROGRESS("진행중"),
    CLOSE("종료");

    private final String progressStatus;

    SessionProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public boolean isRecruitable() {
        return !this.equals(CLOSE);
    }

    public static SessionProgressStatus of(String status) {
        return Arrays.stream(values()).filter(s -> status.equals(s.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 강의 상태입니다."));
    }
}

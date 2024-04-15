package nextstep.courses.domain.status;

import nextstep.courses.exception.SessionProgressStatusInvalidException;

import java.util.Arrays;

public enum ProgressStatus {

    PREPARING("준비중"),
    ACTIVE("진행중"),
    FINISHED("종료")
    ;

    private final String status;

    ProgressStatus(String status) {
        this.status = status;
    }

    public static ProgressStatus convert(String status) {
        return Arrays.stream(values())
                .filter(progressStatus -> progressStatus.status.equals(status))
                .findAny()
                .orElseThrow(() -> new SessionProgressStatusInvalidException(status));
    }

    public String get() {
        return status;
    }

}

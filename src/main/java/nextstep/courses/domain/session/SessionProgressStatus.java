package nextstep.courses.domain.session;

import lombok.Getter;

@Getter
public enum SessionProgressStatus {
    PREPARING("준비중"),
    IN_PROGRESS("진행중"),
    CLOSED("종료");

    private final String description;

    SessionProgressStatus(String description) {
        this.description = description;
    }

    public boolean isInProgress() {
        return this == IN_PROGRESS;
    }
} 
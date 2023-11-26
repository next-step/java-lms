package nextstep.courses.domain.code;

import nextstep.courses.exception.CanNotApplySessionStatusException;

public enum SessionStatus {

    PREPARING("준비중", false),
    RECRUITING("모집중", true),
    END("종료", false);

    private final String description;
    private final boolean canApply;

    SessionStatus(String description,
                  boolean canApply) {
        this.description = description;
        this.canApply = canApply;
    }

    public String getDescription() {
        return this.description;
    }

    public void validateApply() {
        if (!this.canApply) {
            throw new CanNotApplySessionStatusException("수강 신청이 가능한 상태가 아닙니다.");
        }
    }

}

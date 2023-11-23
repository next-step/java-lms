package nextstep.courses.domain;

import nextstep.courses.CanNotApplySessionStatusException;

import java.util.function.Supplier;

public enum SessionStatus {

    PREPARING("준비중", () -> false),
    RECRUITING("모집중", () -> true),
    END("종료", () -> false);

    private final String description;
    private final Supplier<Boolean> canApply;

    SessionStatus(String description,
                  Supplier<Boolean> canApply) {
        this.description = description;
        this.canApply = canApply;
    }

    public String getDescription() {
        return this.description;
    }

    public void validateApply() {
        if (!canApply()) {
            throw new CanNotApplySessionStatusException("수강 신청이 가능한 상태가 아닙니다.");
        }
    }

    private boolean canApply() {
        return this.canApply.get();
    }

}

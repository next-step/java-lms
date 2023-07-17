package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum RecruitmentState {
    RECRUITING(1),
    NOT_RECRUITING(9);

    private int state;

    RecruitmentState(int state) {
        this.state = state;
    }

    public static RecruitmentState of(int state) {
        return (RecruitmentState) Arrays.stream(RecruitmentState.values())
                .filter(recruitmentState -> recruitmentState.getInt() == state)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid RecruitmentState value: " + state));
    }

    public int getInt() {
        return state;
    }
}

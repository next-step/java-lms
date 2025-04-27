package nextstep.courses.domain.session;

import lombok.Getter;

@Getter
public enum SessionRecruitmentStatus {
    RECRUITING("모집중"),
    NOT_RECRUITING("비모집중");

    private final String description;

    SessionRecruitmentStatus(String description) {
        this.description = description;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
} 
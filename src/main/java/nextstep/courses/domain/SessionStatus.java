package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionProgressStatus;
import nextstep.courses.domain.type.SessionRecruitingStatus;
import nextstep.courses.exception.InvalidSessionStatusException;

import java.util.Objects;

public class SessionStatus {

    private SessionProgressStatus progressStatus;
    private SessionRecruitingStatus recruitingStatus;

    public SessionStatus(SessionProgressStatus progressStatus, SessionRecruitingStatus recruitingStatus) {
        this.progressStatus = progressStatus;
        this.recruitingStatus = recruitingStatus;
        validate();
    }

    public void validate() {
        if (progressStatus.isTerminal() && recruitingStatus.isRecruiting()) {
            throw new InvalidSessionStatusException("유효하지 않은 강의 상태입니다.");
        }
    }

    public boolean isRecruiting() {
        return this.recruitingStatus.isRecruiting();
    }

    public SessionProgressStatus progress() {
        return this.progressStatus;
    }

    public SessionRecruitingStatus recruiting() {
        return this.recruitingStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionStatus)) return false;
        SessionStatus that = (SessionStatus) o;
        return progressStatus == that.progressStatus && recruitingStatus == that.recruitingStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(progressStatus, recruitingStatus);
    }

    @Override
    public String toString() {
        return "SessionStatus{" +
            "progressStatus=" + progressStatus +
            ", recruitingStatus=" + recruitingStatus +
            '}';
    }
}

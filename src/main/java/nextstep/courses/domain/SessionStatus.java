package nextstep.courses.domain;

import nextstep.courses.domain.enums.ProgressState;
import nextstep.courses.domain.enums.RecruitmentState;
import nextstep.courses.exception.EnrollFullException;
import nextstep.courses.exception.SessionNotOpenException;
import nextstep.users.domain.NsUser;

public class SessionStatus {
    private final int maxCapacity;
    private ProgressState progressState;
    private RecruitmentState recruitmentState;
    private Enrollments enrollments = new Enrollments();

    public SessionStatus(int maxCapacity, ProgressState progressState, RecruitmentState recruitmentState) {
        this.maxCapacity = maxCapacity;
        this.progressState = progressState;
        this.recruitmentState = recruitmentState;
    }

    public SessionStatus(int maxCapacity) {
        this(maxCapacity, ProgressState.PREPARING, RecruitmentState.RECRUITING);
    }

    public int getEnrollmentSize() {
        return enrollments.getSize();
    }

    public void  enroll(NsUser student, long sessionId) {
        if (progressState.equals(ProgressState.END)) {
            throw new SessionNotOpenException("강의가 종료되어 신청이 불가합니다.");
        }

        if (recruitmentState.equals(RecruitmentState.NOT_RECRUITING)) {
            throw new SessionNotOpenException("강의가 비모집중으로 신청이 불가합니다.");
        }

        if (enrollments.getSize() >= maxCapacity) {
            throw new EnrollFullException("최대 수강 인원을 초과하여 신청이 불가합니다.");
        }

        enrollments.enroll(student, sessionId);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}

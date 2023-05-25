package nextstep.courses.domain;

import nextstep.courses.RegisterCourseException;

public class SessionInfo {
    private final int maxStudents;
    private int currentStudents;
    private SessionStatusType sessionStatusType;

    public SessionInfo(int maxStudents, int currentStudents, SessionStatusType sessionStatusType) {
        this.maxStudents = maxStudents;
        this.currentStudents = currentStudents;
        this.sessionStatusType = sessionStatusType;
    }

    public void registerSession(int studentsCount) {
        this.checkStatusToRegister();
        this.checkMaxStudentsCountToRegister(studentsCount);

        this.currentStudents += studentsCount;
    }

    private void checkStatusToRegister() {
        if (this.sessionStatusType == SessionStatusType.RECRUITING) {
            return;
        }

        throw new RegisterCourseException("수강신청은 모집중일때만 신청 가능합니다.");
    }

    private void checkMaxStudentsCountToRegister(int studentsCount) {
        if (this.currentStudents + studentsCount > this.maxStudents) {
            throw new RegisterCourseException("최대 수강신청 인원을 초과할 수 없습니다.");
        }
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    public SessionStatusType getSessionStatusType() {
        return sessionStatusType;
    }
}

package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

public class SessionEnrollment {

    private boolean isFree;

    private final int maxStudentCount;

    private SessionPrice sessionPrice;

    private SessionStatus sessionStatus;

    private SessionStudents sessionStudents;

    public SessionEnrollment(boolean isFree, int maxStudnetCount, SessionPrice sessionPrice, SessionStatus sessionStatus, SessionStudents sessionStudents) {
        this.isFree = isFree;
        this.maxStudentCount = maxStudnetCount;
        this.sessionPrice = sessionPrice;
        this.sessionStatus = sessionStatus;
        this.sessionStudents = sessionStudents;
    }

    public void enrollFreeSession(NsUser student) {
        validateStatus();
        sessionStudents.add(student);
    }

    public void enrollPaySession(NsUser student, Long userPay) {
        validateStatus();
        validateSize();
        sessionPrice.validatePrice(userPay);
        sessionStudents.add(student);
    }

    private void validateSize() {
        if (sessionStudents.enrolledStudentsCount() >= maxStudentCount) {
            throw new IllegalArgumentException("수강 인원이 다 찼습니다");
        }
    }

    private void validateStatus() {
        if (!sessionStatus.canApply()) {
            throw new IllegalArgumentException("현재 강의는 모집중이 아닙니다");
        }
    }

    public boolean isFree() {
        return isFree;
    }
}

package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

public class SessionEnrollment {

    private boolean isFree;

    private SessionStudent sessionStudent;

    private SessionPrice sessionPrice;

    private SessionStatus sessionStatus;

    public SessionEnrollment(boolean isFree, SessionStudent sessionStudent, SessionPrice sessionPrice, SessionStatus sessionStatus) {
        this.isFree = isFree;
        this.sessionStudent = sessionStudent;
        this.sessionPrice = sessionPrice;
        this.sessionStatus = sessionStatus;
    }

    public void enrollFreeSession(NsUser studnet) {
        validateStatus();
        sessionStudent.add(studnet);
    }

    public void enrollPaySession(NsUser student, Long userPay) {
        validateStatus();
        sessionStudent.isUnderMaxStudentCount();
        sessionPrice.validatePrice(userPay);
        sessionStudent.add(student);
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

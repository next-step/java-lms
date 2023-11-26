package nextstep.courses.domain;

import nextstep.courses.CannotRecruitException;
import nextstep.users.domain.NsUser;

public class SessionEnrolmentInfo {

    private SessionStudentInfo sessionStudentInfo;
    private SessionStatusType sessionStatusType;
    private SessionAmount amount;
    private boolean isFree;

    public SessionEnrolmentInfo(SessionStudentInfo sessionStudentInfo, SessionStatusType sessionStatusType, SessionAmount amount, boolean isFree) {
        this.sessionStudentInfo = sessionStudentInfo;
        this.sessionStatusType = sessionStatusType;
        this.amount = amount;
        this.isFree = isFree;
    }

    public void freeEnrolment(NsUser student) {
        defaultValidate();
        this.sessionStudentInfo.add(student);
    }

    public void payEnrolment(NsUser student, Long userPayed) {
        defaultValidate();
        validatePay(userPayed);

        this.sessionStudentInfo.add(student);
    }

    public boolean isFree() {
        return isFree;
    }

    private void defaultValidate() {
        if (!this.sessionStatusType.isRecruitment()) {
            throw new CannotRecruitException("현재 강의가 모집중인 상태가 아닙니다.");
        }
    }

    private void validatePay(Long userPayed) {
        if (isFullStudents()) {
            throw new CannotRecruitException("강의 최대 수강 인원이 모두 찼습니다.");
        }

        if (isInCorrectAmount(userPayed)) {
            throw new CannotRecruitException("결제금액과 강의금액이 맞지 않습니다.");
        }
    }

    private boolean isInCorrectAmount(Long userPayed) {
        return !this.amount.isCorrectAmount(userPayed);
    }

    private boolean isFullStudents() {
        return this.sessionStudentInfo.isMaxStudents();
    }
}

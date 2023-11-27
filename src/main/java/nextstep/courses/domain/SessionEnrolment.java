package nextstep.courses.domain;

import nextstep.courses.CannotRecruitException;
import nextstep.courses.InvalidValueException;
import nextstep.users.domain.NsUser;

public class SessionEnrolment {

    private SessionStuden sessionStuden;
    private SessionStatusType sessionStatusType;
    private Amount amount;
    private boolean isFree;

    public SessionEnrolment(SessionStuden sessionStuden, SessionStatusType sessionStatusType, Amount amount, boolean isFree) {
        this.sessionStuden = sessionStuden;
        this.sessionStatusType = sessionStatusType;
        this.amount = amount;
        this.isFree = isFree;
    }

    public void freeEnrolment(NsUser student) {
        try {
            defaultValidate();
            this.sessionStuden.add(student);
        } catch (InvalidValueException e) {
            throw new CannotRecruitException(e.getMessage());
        }
    }

    public void payEnrolment(NsUser student, Long userPayed) {
        try {
            defaultValidate();
            validatePay(userPayed);

            this.sessionStuden.add(student);
        } catch (InvalidValueException e) {
            throw new CannotRecruitException(e.getMessage());
        }
    }

    public boolean isFree() {
        return isFree;
    }

    private void defaultValidate() {
        if (!this.sessionStatusType.isRecruitment()) {
            throw new InvalidValueException("현재 강의가 모집중인 상태가 아닙니다.");
        }
    }

    private void validatePay(Long userPayed) {
        if (isFullStudents()) {
            throw new InvalidValueException("강의 최대 수강 인원이 모두 찼습니다.");
        }

        if (isInCorrectAmount(userPayed)) {
            throw new InvalidValueException("결제금액과 강의금액이 맞지 않습니다.");
        }
    }

    private boolean isInCorrectAmount(Long userPayed) {
        return !this.amount.isCorrectAmount(userPayed);
    }

    private boolean isFullStudents() {
        return this.sessionStuden.isMaxStudents();
    }
}

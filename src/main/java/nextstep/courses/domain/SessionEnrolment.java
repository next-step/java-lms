package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class SessionEnrolment {

    private SessionStudent sessionStudent;
    private SessionStatusType sessionStatusType;
    private Amount amount;
    private boolean isFree;

    public SessionEnrolment(SessionStudent sessionStudent, SessionStatusType sessionStatusType, Amount amount, boolean isFree) {
        this.sessionStudent = sessionStudent;
        this.sessionStatusType = sessionStatusType;
        this.amount = amount;
        this.isFree = isFree;
    }

    public void freeEnrolment(NsUser student) {
        defaultValidate();
        this.sessionStudent.add(student);
    }

    public void payEnrolment(NsUser student, Long userPayed) {
        defaultValidate();
        validatePay(userPayed);

        this.sessionStudent.add(student);
    }

    public boolean isFree() {
        return isFree;
    }

    public int totalStudent() {
        return this.sessionStudent.studentCount();
    }

    public boolean isFullStudents() {
        return this.sessionStudent.isMaxStudents();
    }

    public String sessionStatusType() {
        return this.sessionStatusType.toString();
    }

    public Long amount() {
        return this.amount.amount();
    }

    private void defaultValidate() {
        if (!this.sessionStatusType.isRecruitment()) {
            throw new java.lang.IllegalArgumentException("현재 강의가 모집중인 상태가 아닙니다.");
        }
    }

    private void validatePay(Long userPayed) {
        if (isFullStudents()) {
            throw new java.lang.IllegalArgumentException("강의 최대 수강 인원이 모두 찼습니다.");
        }

        if (isInCorrectAmount(userPayed)) {
            throw new java.lang.IllegalArgumentException("결제금액과 강의금액이 맞지 않습니다.");
        }
    }

    private boolean isInCorrectAmount(Long userPayed) {
        return !this.amount.isCorrectAmount(userPayed);
    }

}

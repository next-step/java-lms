package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class SessionEnrolment {

    private SessionStudents sessionStudents;
    private SessionStatus sessionStatus;
    private Amount amount;
    private boolean isFree;

    public SessionEnrolment(SessionStudents sessionStudents, SessionStatusType sessionStatusType, Amount amount, boolean isFree) {
        this(sessionStudents, new SessionStatus(sessionStatusType), amount, isFree);
    }

    public SessionEnrolment(SessionStudents sessionStudents, SessionStatus sessionStatus, Amount amount, boolean isFree) {
        this.sessionStudents = sessionStudents;
        this.sessionStatus = sessionStatus;
        this.amount = amount;
        this.isFree = isFree;
    }

    public void enrolment(Long userPayed) {
        if (isFree) {
            defaultValidate();
            return;
        }
        defaultValidate();
        validatePay(userPayed);
    }

    public void addStudent(NsUser student) {
        this.sessionStudents.add(student);
    }

    public boolean isFree() {
        return isFree;
    }

    public int totalStudent() {
        return this.sessionStudents.studentCount();
    }

    public boolean isFullStudents() {
        return this.sessionStudents.isMaxStudents();
    }

    public String sessionStatusType() {
        return this.sessionStatus.sessionStatusType();
    }

    public String recruitmentStatusType() {
        return this.sessionStatus.recruitmentStatusType();
    }

    public Long amount() {
        return this.amount.amount();
    }

    private void defaultValidate() {
        if (!this.sessionStatus.isRecruitment()) {
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

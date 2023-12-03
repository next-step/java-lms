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

    public void enrolment(NsUser student, Long userPayed) {
        validate(userPayed);
        this.sessionStudents.add(student);
    }

    public void approve(Student student) {
        this.sessionStudents.approve(student);
    }

    public void refuse(Student student) {
        this.sessionStudents.refuse(student);
    }

    public boolean isFree() {
        return isFree;
    }

    public int maxStudent() {
        return this.sessionStudents.maxStudentsCount();
    }

    public boolean isFullStudents() {
        if (this.isFree) {
            return false;
        }

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

    public int applyStudentsCount() {
        return this.sessionStudents.applyStudentsCount();
    }

    private void validate(Long userPayed) {
        if (!this.sessionStatus.isRecruitment()) {
            throw new IllegalArgumentException("현재 강의가 모집중인 상태가 아닙니다.");
        }

        if (isFullStudents()) {
            throw new IllegalArgumentException("강의 최대 수강 인원이 모두 찼습니다.");
        }

        if (isInCorrectAmount(userPayed)) {
            throw new IllegalArgumentException("결제금액과 강의금액이 맞지 않습니다.");
        }
    }

    private boolean isInCorrectAmount(Long userPayed) {
        if (this.isFree) {
            return false;
        }

        return !this.amount.isCorrectAmount(userPayed);
    }

}

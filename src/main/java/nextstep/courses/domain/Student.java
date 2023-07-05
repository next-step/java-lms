package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Student {
    private Long id;

    private Long sessionId;

    private Long userId;

    private StudentStatus status = StudentStatus.REQUESTED;


    public Student(Session session, NsUser user) {
        this.sessionId = session.getId();
        this.userId = user.getId();
    }

    public Student(Long id, Long sessionId, Long userId, StudentStatus status) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public enum StudentStatus {
        REQUESTED, ENROLLED, CANCELED;

        public boolean isEnrolled() {
            return this == ENROLLED;
        }

        public boolean isCanceled() {
            return this == CANCELED;
        }

        public boolean isRequested() {
            return this == REQUESTED;
        }
    }

    public void applyEnroll() {
        this.status = StudentStatus.ENROLLED;
    }

    public void cancelEnroll() {
        if (this.status.isEnrolled()) {
            throw new IllegalArgumentException("이미 수강신청이 완료된 학생은 취소할 수 없습니다.");
        }

        if (this.status.isCanceled()) {
            throw new IllegalArgumentException("이미 수강신청이 취소된 학생은 취소할 수 없습니다.");
        }

        this.status = StudentStatus.CANCELED;
    }
}

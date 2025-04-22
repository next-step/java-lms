package nextstep.courses.domain;

public class Enrollment {
    private Member student;
    private Session session;
    private EnrollmentStatus status; // PENDING, APPROVED, REJECTED

    public Enrollment(Member student, Session session) {
        this.student = student;
        this.session = session;
        this.status = EnrollmentStatus.PENDING;
    }

    public void approve() {
        if (this.status == EnrollmentStatus.APPROVED) {
            throw new IllegalStateException("이미 승인된 수강 신청입니다.");
        }

        this.status = EnrollmentStatus.APPROVED;
        session.accept();
    }

    public void reject() {
        this.status = EnrollmentStatus.REJECTED;
    }

    public boolean isApproved() {
        return status == EnrollmentStatus.APPROVED;
    }

    public Member getStudent() {
        return student;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public Session getSession() {
        return session;
    }
}


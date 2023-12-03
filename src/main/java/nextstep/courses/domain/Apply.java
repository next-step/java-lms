package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Apply {
    private Long id;
    private final Session session;
    private final NsUser student;
    private ApplyStatus applyStatus;

    public Apply(Session session, NsUser student, ApplyStatus applyStatus) {
        this.session = session;
        this.student = student;
        this.applyStatus = applyStatus;
    }

    public static Apply defaultApply(Session session, NsUser nsUser) {
        return new Apply(session, nsUser, ApplyStatus.APPLYING);
    }

    public void approve(Long instructorId) {
        validate(instructorId);

        this.session.addStudent(this.student);
        this.applyStatus = ApplyStatus.APPROVAL;
    }
    
    public void refuse(Long instructorId) {
        validate(instructorId);

        this.applyStatus = ApplyStatus.REFUSAL;
    }

    public ApplyStatus applyStatus() {
        return this.applyStatus;
    }

    private void validate(Long instructorId) {
        if (!this.session.isInstructor(instructorId)) {
            throw new IllegalArgumentException("해당 강의의 강사만 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apply apply = (Apply) o;
        return Objects.equals(id, apply.id) && Objects.equals(session, apply.session) && Objects.equals(student, apply.student) && applyStatus == apply.applyStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, session, student, applyStatus);
    }
}

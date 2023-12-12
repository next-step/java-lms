package nextstep.courses.domain.session.student;

import java.util.Objects;

public class Student {

    private Long id;
    private Long enrolmentId;
    private Long nsUserId;

    public Student(Long enrolmentId, Long nsUserId) {
        this.enrolmentId = enrolmentId;
        this.nsUserId = nsUserId;
    }

    public Student(Long id, Long enrolmentId, Long nsUserId) {
        this(enrolmentId, nsUserId);
        this.id = id;
    }

    public Long getEnrolmentId() {
        return this.enrolmentId;
    }

    public Long getNsUserId() {
        return this.nsUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(enrolmentId, student.enrolmentId) && Objects.equals(nsUserId, student.nsUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enrolmentId, nsUserId);
    }
}

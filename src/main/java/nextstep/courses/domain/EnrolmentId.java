package nextstep.courses.domain;

import java.util.Objects;

public class EnrolmentId {
    private Long enrolmentId;

    public EnrolmentId() {
    }

    public EnrolmentId(Long enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

    public Long getEnrolmentId() {
        return enrolmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrolmentId other = (EnrolmentId) o;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrolmentId);
    }

    @Override
    public String toString() {
        return "EnrolmentId{" +
                "enrolmentId=" + enrolmentId +
                '}';
    }
}

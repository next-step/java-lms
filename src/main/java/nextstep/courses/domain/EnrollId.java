package nextstep.courses.domain;

import nextstep.utils.DomainId;

import java.util.Objects;

public class EnrollId implements DomainId {
    private Long enrolmentId;

    public EnrollId() {
    }

    public EnrollId(Long enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

    @Override
    public Long value() {
        return enrolmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollId other = (EnrollId) o;
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

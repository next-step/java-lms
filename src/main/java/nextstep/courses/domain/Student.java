package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Student {

    private final NsUser student;

    private ApplyStatus applyStatus;

    public Student(NsUser student) {
        this.student = student;
        this.applyStatus = ApplyStatus.APPLYING;
    }

    public Student(NsUser student, ApplyStatus applyStatus) {
        this.student = student;
        this.applyStatus = applyStatus;
    }

    public void approve() {
        this.applyStatus = ApplyStatus.APPROVAL;
    }

    public boolean isApproval() {
        return this.applyStatus == ApplyStatus.APPROVAL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student1 = (Student) o;
        return Objects.equals(student, student1.student) && applyStatus == student1.applyStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, applyStatus);
    }
}

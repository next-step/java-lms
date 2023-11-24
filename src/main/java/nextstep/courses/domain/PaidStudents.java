package nextstep.courses.domain;

import nextstep.courses.exception.SessionFullException;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class PaidStudents {

    private final int capacity;

    private final Students students;

    public PaidStudents(int capacity) {
        this(capacity, new Students());
    }

    public PaidStudents(int capacity,
                        Students students) {
        this.capacity = capacity;
        this.students = students;
    }

    public void add(NsUser student) {
        if (students.isFull(capacity)) {
            throw new SessionFullException("수강 신청 인원이 마감 되었습니다.");
        }
        students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidStudents paidStudents1 = (PaidStudents) o;
        return capacity == paidStudents1.capacity && Objects.equals(students, paidStudents1.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, students);
    }
}

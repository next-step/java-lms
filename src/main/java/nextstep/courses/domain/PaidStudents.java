package nextstep.courses.domain;

import nextstep.courses.exception.SessionFullException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaidStudents {

    private final int capacity;

    private final List<NsUser> students;

    public PaidStudents(int capacity) {
        this(capacity, new ArrayList<>());
    }

    public PaidStudents(int capacity,
                        List<NsUser> students) {
        if (capacity < students.size()) {
            throw new SessionFullException("수강 신청 인원이 마감 되었습니다.");
        }
        this.capacity = capacity;
        this.students = students;
    }

    public void add(NsUser student) {
        if (isFull()) {
            throw new SessionFullException("수강 신청 인원이 마감 되었습니다.");
        }
        students.add(student);
    }

    private boolean isFull() {
        return students.size() >= capacity;
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

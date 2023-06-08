package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.CannotRegisterException;

import java.util.HashSet;
import java.util.Set;

public class Students {
    private final Set<Student> students;
    private final Capacity capacity;

    public Students(int capacity) {
        this(new HashSet<>(), new Capacity(capacity));
    }

    public Students(Set<Student> students, Capacity capacity) {
        if (capacity.isFull(students.size())) {
            throw new IllegalArgumentException("강의 최대 수강인원인 " + capacity.value() + "명을 초과했습니다");
        }
        this.students = students;
        this.capacity = capacity;
    }

    public void updateCapacity(int value) {
        if (value < students.size()) {
            throw new IllegalStateException("변경하려는 수강 제한 인원이 현재 수강신청한 인원보다 작습니다");
        }
        capacity.update(value);
    }

    public void add(Student student) {
        if (capacity.isFull(students.size())) {
            throw new CannotRegisterException("강의 최대 수강 인원을 초과했습니다");
        }
        if (students.contains(student)) {
            throw new CannotRegisterException("이미 등록한 유저입니다");
        }
        students.add(student);
    }

    public int count() {
        return students.size();
    }

    public int capacity() {
        return capacity.value();
    }
}

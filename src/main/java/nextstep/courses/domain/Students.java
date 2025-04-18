package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final int capacity;
    private final List<Student> students;

    public Students(int capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    private void validateCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }
    }

    public void register(Student student, Long price) {
        if (students.contains(student)) {
            throw new IllegalArgumentException("already enrolled");
        }
        if (students.size() >= capacity) {
            throw new IllegalArgumentException("student limit exceeded");
        }
        student.pay(price);
        students.add(student);
    }

}

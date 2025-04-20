package nextstep.courses.domain.model;

import nextstep.users.domain.NsUser;

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

    public void register(NsUser user, Session session, Long price) {
        if (students.size() >= capacity) {
            throw new IllegalArgumentException("student limit exceeded");
        }

        Student student = new Student(user, session);
        if (students.contains(student)) {
            throw new IllegalArgumentException("already enrolled");
        }

        student.pay(price);
        students.add(student);
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Students{" +
                "capacity=" + capacity +
                ", students=" + students +
                '}';
    }
}

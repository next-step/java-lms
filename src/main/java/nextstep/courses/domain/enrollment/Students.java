package nextstep.courses.domain.enrollment;

import nextstep.courses.AlreadyEnrolledException;
import nextstep.courses.ExceedMaxEnrollmentException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Students {

    private final int capacity;
    private final List<NsUser> students;

    public Students(int capacity) {
        this(capacity, new ArrayList<>());
    }

    public Students(int capacity, List<NsUser> students) {
        if (capacity < students.size()) {
            throw new ExceedMaxEnrollmentException();
        }
        this.capacity = capacity;
        this.students = students;
    }

    public int countEnrollment() {
        return students.size();
    }

    public void enroll(NsUser user) {
        if (isAlreadyEnrolled(user)) {
            throw new AlreadyEnrolledException();
        }
        students.add(user);
        if (isExceededMaxEnrollment()) {
            students.remove(user);
            throw new ExceedMaxEnrollmentException();
        }
    }

    private boolean isAlreadyEnrolled(NsUser user) {
        return students.contains(user);
    }

    public List<NsUser> fetchStudents() {
        return Collections.unmodifiableList(students);
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public boolean isExceededMaxEnrollment() {
        return capacity < countEnrollment();
    }

    public int sessionCapacity() {
        return capacity;
    }
}

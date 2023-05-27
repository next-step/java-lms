package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Students {

    private final int maxEnrollment;
    private final List<NsUser> students;

    public Students(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
        students = new ArrayList<>();
    }

    public int countEnrollmentCount() {
        return students.size();
    }

    public void addStudent(NsUser user) {
        if (isOverCapacity()) {
            throw new IllegalArgumentException("cannot enroll because the maximum capacity has been exceeded");
        }
        students.add(user);
    }

    private boolean isOverCapacity() {
        return countEnrollmentCount() >= maxEnrollment;
    }

    public int maxEnrollmentValue() {
        return maxEnrollment;
    }

    public List<NsUser> fetchStudents() {
        return Collections.unmodifiableList(students);
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }
}

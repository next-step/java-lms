package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Students {

    private final int maxEnrollment;
    private final List<NsUser> students;

    public Students(int maxEnrollment) {
        this(maxEnrollment, new ArrayList<>());
    }

    public Students(int maxEnrollment, List<NsUser> students) {
        if (maxEnrollment < students.size()) {
            throw new IllegalArgumentException("can not exceed the maximum enrollment");
        }
        this.maxEnrollment = maxEnrollment;
        this.students = students;
    }

    public int countEnrollment() {
        return students.size();
    }

    public void enroll(NsUser user) {
        students.add(user);
        if (isExceededMaxEnrollment()) {
            students.remove(user);
            throw new IllegalArgumentException("can not exceed the maximum enrollment");
        }
    }

    public List<NsUser> fetchStudents() {
        return Collections.unmodifiableList(students);
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public boolean isExceededMaxEnrollment() {
        return maxEnrollment < countEnrollment();
    }
}

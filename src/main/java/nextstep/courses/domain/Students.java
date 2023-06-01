package nextstep.courses.domain;

import nextstep.courses.AlreadyEnrolledException;
import nextstep.courses.ExceedMaxEnrollmentException;
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
            throw new ExceedMaxEnrollmentException();
        }
        this.maxEnrollment = maxEnrollment;
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
        return maxEnrollment < countEnrollment();
    }
}

package nextstep.courses.domain;

import nextstep.courses.exception.SessionRegistrationException;

import java.util.ArrayList;
import java.util.List;

public class SessionStudents {
    private List<Student> students = new ArrayList<>();
    private int maximumNumber;

    SessionStudents() {
    }

    public SessionStudents(int maximumNumber) {
        this.maximumNumber = maximumNumber;
    }

    public int countStudents() {
        return students.size();
    }

    public boolean addStudent(Student student) {
        checkExceedLimit();
        return students.add(student);
    }

    public void checkExceedLimit() {
        if (countStudents() >= maximumNumber) {
            throw new SessionRegistrationException("정원이 초과되었습니다.");
        }
    }

    public int getMaximumNumber() {
        return maximumNumber;
    }
}

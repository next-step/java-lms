package nextstep.courses.domain;

import nextstep.courses.exception.DuplicateStudentException;
import nextstep.courses.exception.SignUpFullException;

import java.util.ArrayList;
import java.util.List;

public class SessionStatus {
    private int maxCapacity;
    private Students students = new Students();

    public SessionStatus(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getStudentsSize() {
        return students.getSize();
    }

    public void signUp(Student student) {
        if (students.getSize() >= maxCapacity) {
            throw new SignUpFullException("최대 수강 인원을 초과하여 신청이 불가합니다.");
        }

        students.add(student);
    }
}

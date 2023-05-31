package nextstep.courses.domain;

import nextstep.courses.exception.DuplicateStudentException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollments {
    List<Enrollment> enrollments = new ArrayList<>();

    public void enroll(NsUser student, long sessionId) {
        Enrollment enrollment = new Enrollment(student, sessionId);

        if (enrollments.contains(enrollment)) {
            throw new DuplicateStudentException("이미 강의에 등록된 유저입니다.");
        }

        enrollments.add(enrollment);
    }

    public int getSize() {
        return enrollments.size();
    }
}

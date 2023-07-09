package nextstep.courses.domain;

import nextstep.courses.exception.DuplicateStudentException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Enrollments {
    List<Enrollment> enrollments = new ArrayList<>();

    public Enrollment enroll(long sessionId, NsUser student) {
        Enrollment enrollment = new Enrollment(sessionId, student);

        if (enrollments.contains(enrollment)) {
            throw new DuplicateStudentException("이미 강의에 등록된 유저입니다.");
        }

        enrollments.add(enrollment);
        return enrollment;
    }

    public int getSize() {
        return enrollments.size();
    }

    public void approve(long sessionId, NsUser student) {
        Enrollment enrollment = get(sessionId, student);
        enrollment.approve();
    }

    private Enrollment get(long sessionId, NsUser student) {
        Enrollment enrollment = new Enrollment(sessionId, student);
        return enrollments.stream().filter(x -> x.equals(enrollment))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("세션ID " + sessionId + " 에 " + student.getName()
                        + " 학생이 등록되지 않았습니다."));
    }

    public void cancel(long sessionId, NsUser student) {
        Enrollment enrollment = get(sessionId, student);
        enrollment.cancel();
    }

    public long getApprovalCount(long sessionId) {
        return enrollments.stream().filter(x -> x.isSameSession(sessionId) && x.isApproved())
                .count();
    }
}

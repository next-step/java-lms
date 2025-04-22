package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Enrollments {
    private final List<Enrollment> values = new ArrayList<>();

    public void addEnrollment(Enrollment enrollment) {
        values.add(enrollment);
    }

    public boolean isEnrolledBy(Member student) {
        return values.stream()
                .anyMatch(e -> e.getStudent().equals(student));
    }

    public Enrollment findByMember(Member student) {
        return values.stream()
                .filter(e -> e.getStudent().equals(student))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 회원의 수강 신청이 존재하지 않습니다."));
    }

    public List<Enrollment> getValues() {
        return Collections.unmodifiableList(values);
    }
}

package nextstep.sessions.domain.enrollment;

import java.util.HashSet;
import java.util.Set;

public class Enrollments {

    static final String ERROR_ALREADY_ENROLLED = "이미 등록된 사용자입니다.";

    private final Set<Enrollment> enrollments = new HashSet<>();

    public void add(Enrollment enrollment) {
        if (enrollments.contains(enrollment)) {
            throw new IllegalArgumentException(ERROR_ALREADY_ENROLLED);
        }
        enrollments.add(enrollment);
    }

    public int size() {
        return enrollments.size();
    }
}


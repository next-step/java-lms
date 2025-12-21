package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Enrollments {

    private final List<Enrollment> enrollments;
    private final Capacity capacity;

    public Enrollments(Capacity capacity) {
        this(new ArrayList<>(), capacity);
    }

    public Enrollments(List<Enrollment> enrollments, Capacity capacity) {
        this.enrollments = enrollments;
        this.capacity = capacity;
    }

    public void validateEnroll(Long userId) {
        if (EnrolledCheck(userId)) {
            throw new IllegalStateException();
        }

        capacity.validateAvailable();
    }

    public Enrollment add(Long sessionId, Long userId) {
        validateEnroll(userId);

        Enrollment enrollment = new Enrollment(sessionId, userId);
        enrollments.add(enrollment);
        capacity.increase();

        return enrollment;
    }

    private boolean EnrolledCheck(Long userId) {
        return enrollments.stream()
                .anyMatch(enrollment -> enrollment.isSameUser(userId));
    }
}



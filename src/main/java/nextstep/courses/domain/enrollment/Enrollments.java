package nextstep.courses.domain.enrollment;

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

    public Enrollment enroll(Long sessionId, Long userId) {
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

    public int getCapacity() {
        return capacity.value();
    }

    public Enrollment approve(Long userId) {
        Enrollment enrollment = findByUserId(userId);
        enrollment.approve();

        return enrollment;
    }

    public Enrollment reject(Long userId) {
        Enrollment enrollment = findByUserId(userId);
        enrollment.reject();

        return enrollment;
    }

    private Enrollment findByUserId(Long userId) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}



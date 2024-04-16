package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static nextstep.courses.ExceptionMessage.SESSION_ENROLL_FAIL;

public class Enrollments {
    private final List<Enrollment> enrollments;

    public Enrollments() {
        this(new ArrayList<>());
    }

    public Enrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public long numberOfCurrentEnrollment() {
        return enrollments.stream()
                .filter(Enrollment::isApproved)
                .count();
    }

    public void add(Session session, NsUser user) {
        validateDuplicateUser(user);
        enrollments.add(new Enrollment(session, user));
    }

    private void validateDuplicateUser(NsUser user) {
        if (isDuplicatedUser(user)) {
            throw new CannotEnrollException(SESSION_ENROLL_FAIL.message());
        }
    }

    private boolean isDuplicatedUser(NsUser user) {
        return enrollments.stream()
                .map(Enrollment::getEnrolledUser)
                .collect(Collectors.toSet())
                .contains(user);
    }

    public List<Enrollment> getEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }
}

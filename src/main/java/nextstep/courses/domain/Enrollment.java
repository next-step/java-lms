package nextstep.courses.domain;

import nextstep.courses.domain.enums.ApprovalStatus;
import nextstep.courses.domain.enums.EnrollmentStatus;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Enrollment {
    private List<UserEnrollment> users = new LinkedList<>();
    private EnrollmentStatus enrollmentStatus;
    private final int maximumEnrollment;

    public Enrollment(int maximumEnrollment) {
        this.maximumEnrollment = maximumEnrollment;
    }

    public Enrollment(List<UserEnrollment> users, EnrollmentStatus enrollmentStatus, int maximumEnrollment) {
        this.users = users;
        this.enrollmentStatus = enrollmentStatus;
        this.maximumEnrollment = maximumEnrollment;
    }

    public void enroll(User user) throws SessionEnrollmentException {
        this.users.add(new UserEnrollment(user, ApprovalStatus.PENDING));
    }

    public boolean canEnroll() {
        return this.getEnrollmentStatus().canEnroll();
    }

    public void approveUser(User user) {
        this.getUserEnrollment(user)
                .ifPresent(UserEnrollment::approved);
    }

    public void disApproveUser(User user) {
        this.getUserEnrollment(user)
                .ifPresent(UserEnrollment::disApproved);
    }

    private Optional<UserEnrollment> getUserEnrollment(User user) {
        return this.users.stream()
                .filter(userEnrollment -> userEnrollment.isSameUser(user))
                .findFirst();
    }

    public List<User> getUsers() {
        return users.stream()
                .map(UserEnrollment::getUser)
                .collect(Collectors.toList());
    }

    public List<UserEnrollment> getUserEnrollments() {
        return this.users;
    }

    public int getMaximumEnrollment() {
        return maximumEnrollment;
    }

    public User getLatestEnrollmentUser() {
        if (this.users.isEmpty()) {
            return null;
        }

        int lastIndex = this.users.size();
        return this.users.get(lastIndex - 1).getUser();
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public boolean canApproved() throws SessionEnrollmentException {
        long count = this.users.stream()
                .filter(UserEnrollment::isApproved)
                .count();

        return count < this.maximumEnrollment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return maximumEnrollment == that.maximumEnrollment && Objects.equals(users, that.users) && enrollmentStatus == that.enrollmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, enrollmentStatus, maximumEnrollment);
    }
}

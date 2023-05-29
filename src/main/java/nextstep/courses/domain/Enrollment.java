package nextstep.courses.domain;

import nextstep.courses.domain.enums.ApprovalStatus;
import nextstep.courses.domain.enums.EnrollmentStatus;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Enrollment {
    private List<UserEnrollment> users = new LinkedList<>();
    private EnrollmentStatus enrollmentStatus;
    private final int maximumEnrollment;

    public Enrollment(int maximumEnrollment) {
        this.maximumEnrollment = maximumEnrollment;
    }

    public Enrollment(List<UserEnrollment> users, int maximumEnrollment) {
        this.users = users;
        this.maximumEnrollment = maximumEnrollment;
    }

    public Enrollment(List<UserEnrollment> users, EnrollmentStatus enrollmentStatus, int maximumEnrollment) {
        this.users = users;
        this.enrollmentStatus = enrollmentStatus;
        this.maximumEnrollment = maximumEnrollment;
    }

    public void enroll(User user) throws SessionEnrollmentException {
        checkEnrollment();

        this.users.add(new UserEnrollment(user, ApprovalStatus.PENDING));
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

    private void checkEnrollment() throws SessionEnrollmentException {
        if (this.users.size() >= this.maximumEnrollment) {
            throw new SessionEnrollmentException(String.format("최대 수강 인원인 '%d명'을 초과했습니다.", maximumEnrollment));
        }
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

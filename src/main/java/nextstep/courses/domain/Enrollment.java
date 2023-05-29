package nextstep.courses.domain;

import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Enrollment {
    private List<User> users = new LinkedList<>();
    private int maximumEnrollment;

    public Enrollment(int maximumEnrollment) {
        this.maximumEnrollment = maximumEnrollment;
    }

    public Enrollment(List<User> users, int maximumEnrollment) {
        this.users = users;
        this.maximumEnrollment = maximumEnrollment;
    }

    public void enroll(User user) throws SessionEnrollmentException {
        checkEnrollment();

        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public int getMaximumEnrollment() {
        return maximumEnrollment;
    }

    public void updateUsers(List<User> users) {
        this.users = users;
    }

    public User getLatestEnrollmentUser() {
        if (this.users.isEmpty()) {
            return null;
        }

        int lastIndex = this.users.size();
        return this.users.get(lastIndex - 1);
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
        return maximumEnrollment == that.maximumEnrollment && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, maximumEnrollment);
    }
}

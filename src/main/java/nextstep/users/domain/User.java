package nextstep.users.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.application.model.req.ReqChangeUserProfile;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private final long id;
    private final UserType userType;
    private final String userId;
    private final LocalDate createdAt;

    private String password;
    private LocalDate updatedAt;

    private User(long id, UserType userType, String userId, String password, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.userType = userType;
        this.userId = userId;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User of(UserType userType, String userId, String password) {
        long id = SimpleIdGenerator.getAndIncrement(User.class);
        return new User(id, userType, userId, password, LocalDate.now(), LocalDate.now());
    }

    public static User of(long id, UserType userType, String userId, String password, LocalDate createdAt, LocalDate updatedAt) {
        return new User(id, userType, userId, password, createdAt, updatedAt);
    }

    public boolean isUser(User requestor) {
        return equals(requestor);
    }

    public User changePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("비밀번호는 빈값이 허용되질 않아요 :(");
        }

        if (password.isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 빈값이 허용되질 않아요 :(");
        }

        if (this.password.equals(password)) {
            throw new IllegalArgumentException("동일한 비밀번호 입니다 :(");
        }

        this.password = password;
        this.updatedAt = LocalDate.now();
        return this;
    }

    public Student updateStudent(Student student, ReqChangeUserProfile reqChangeUserProfile) {
        student = student.update(reqChangeUserProfile);
        this.updatedAt = LocalDate.now();
        return student;
    }

    public Instructor updateInstructor(Instructor instructor, ReqChangeUserProfile reqChangeUserProfile) {
        instructor = instructor.update(reqChangeUserProfile);
        this.updatedAt = LocalDate.now();

        return instructor;
    }

    public boolean isGuestUser() {
        return userType.isGuest();
    }

    public boolean isAllowOpenSession() {
        return userType.isAllowOpenSession();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(userId, user.userId) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, password);
    }
}

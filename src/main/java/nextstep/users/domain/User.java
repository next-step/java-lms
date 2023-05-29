package nextstep.users.domain;

import nextstep.users.application.model.req.ReqChangeUserProfile;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    public static final GuestUser GUEST_USER = new GuestUser();

    private long id;

    private String userId;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public User() {
    }

    public User(Long id, String userId, String password, String name, String email) {
        this(id, userId, password, name, email, LocalDateTime.now(), null);
    }

    public User(Long id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }


    public boolean matchUser(User target) {
        return matchUserId(target.getUserId());
    }

    private boolean matchUserId(String userId) {
        return this.userId.equals(userId);
    }

    public boolean matchPassword(String targetPassword) {
        return password.equals(targetPassword);
    }

    public boolean isUser(User requestor) {
        return equals(requestor);
    }

    public Student updateStudent(Student student, ReqChangeUserProfile reqChangeUserProfile) {
        return student.update(reqChangeUserProfile);
    }

    public Instructor updateInstructor(Instructor instructor, ReqChangeUserProfile reqChangeUserProfile) {
        return instructor.update(reqChangeUserProfile);
    }

    public boolean isGuestUser() {
        return false;
    }

    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
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

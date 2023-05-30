package nextstep.users.domain;

import nextstep.users.domain.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UserStatus userStatus;

    public User() {
    }

    public User(Long id, String userId, String password, String name, String email) {
        this(id, userId, password, name, email, LocalDateTime.now(), null);
    }

    public User(Long id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(Long id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt, UserStatus userStatus) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userStatus = userStatus;
    }

    public User(Long id, String userId, String password, String name, String email, UserStatus userStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.userStatus = userStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public boolean isSelected() {
        if (this.userStatus == null) {
            return true;
        }

        return !userStatus.isNotSelected();
    }

    @Override
    public String toString() {
        return "NsUser{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userId, user.userId) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt) && userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, password, name, email, createdAt, updatedAt, userStatus);
    }
}

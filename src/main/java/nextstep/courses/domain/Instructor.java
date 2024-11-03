package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Instructor {
    private final long id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Instructor(String userId,
                      String password,
                      String name,
                      String email) {
        this(0L, userId, password, name, email, LocalDateTime.now(), LocalDateTime.now());
    }

    public Instructor(long id,
                      String userId,
                      String password,
                      String name,
                      String email,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt
                      ) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return id == that.id && Objects.equals(userId, that.userId) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, password, name, email);
    }
}

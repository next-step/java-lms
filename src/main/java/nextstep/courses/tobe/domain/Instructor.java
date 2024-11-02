package nextstep.courses.tobe.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Instructor {
    private final long id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
    private final long creatorId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Instructor(String userId,
                      String password,
                      String name,
                      String email,
                      long creatorId) {
        this(0L, userId, password, name, email, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Instructor(long id,
                      String userId,
                      String password,
                      String name,
                      String email,
                      long creatorId,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt
                      ) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return id == that.id && creatorId == that.creatorId && Objects.equals(userId, that.userId) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, password, name, email, creatorId);
    }

    public long getId() {
        return id;
    }
}

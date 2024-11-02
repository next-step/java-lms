package nextstep.courses.tobe.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Instructor {
    private final long id;
    private final String instructorId;
    private final String password;
    private final String name;
    private final String email;
    private final long creatorId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Instructor(String instructorId,
                      String password,
                      String name,
                      String email,
                      long creatorId) {
        this(0L, instructorId, password, name, email, creatorId, LocalDateTime.now());
    }

    public Instructor(long id,
                      String instructorId,
                      String password,
                      String name,
                      String email,
                      long creatorId,
                      LocalDateTime createdAt) {
        this.id = id;
        this.instructorId = instructorId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return id == that.id && creatorId == that.creatorId && Objects.equals(instructorId, that.instructorId) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instructorId, password, name, email, creatorId);
    }
}

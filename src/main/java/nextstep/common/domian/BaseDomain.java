package nextstep.common.domian;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class BaseDomain {
    protected String id;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    protected boolean deleted = false;

    public BaseDomain() {
    }

    public BaseDomain(String id) {
        this(id, LocalDateTime.now(), LocalDateTime.now());
    }

    public BaseDomain(String id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BaseDomain(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public Long id() {
        if (id == null) {
            return null;
        }
        return Long.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDomain that = (BaseDomain) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt);
    }
}

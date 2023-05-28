package nextstep.users.domain;

import nextstep.qna.UnAuthorizedException;

import java.time.LocalDateTime;
import java.util.Objects;

public class NsUser {

    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public NsUser() {
    }

    public NsUser(Long id, String userId, String password, String name, String email) {
        this(id, userId, password, name, email, LocalDateTime.now(), null);
    }

    public NsUser(Long id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NsUser nsUser = (NsUser) o;
        return Objects.equals(id, nsUser.id) && Objects.equals(userId,
                nsUser.userId) && Objects.equals(name, nsUser.name)
                && Objects.equals(email, nsUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, email);
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
}

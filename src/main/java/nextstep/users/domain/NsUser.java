package nextstep.users.domain;

import lombok.Getter;
import nextstep.common.domian.BaseDomain;
import nextstep.qna.UnAuthorizedException;

import java.time.LocalDateTime;
import java.util.Objects;


@Getter
public class NsUser extends BaseDomain {
    public static final GuestNsUser GUEST_USER = new GuestNsUser();

    private String userId;

    private String password;

    private String name;

    private String email;

    private NsUserType type;

    public NsUser() {
    }

    public NsUser(String userId, String password, String name, String email) {
        this(null, userId, password, name, email, LocalDateTime.now(), null);
    }

    public NsUser(String id, String userId, String password, String name, String email) {
        this(id, userId, password, name, email, LocalDateTime.now(), null);
    }

    public NsUser(String id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, userId, password, name, email, createdAt, updatedAt, "모름");
    }

    public NsUser(String id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt, String type) {
        super(id, createdAt, updatedAt);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = NsUserType.fromString(type);
    }

    public boolean canApprove(NsUser nsUser) {
        return this.type.canApprove(nsUser.type);
    }

    public boolean canCancel(NsUser nsUser) {
        return this.type.canCancel(nsUser.type);
    }

    public String getType() {
        return type.getType();
    }

    public void update(NsUser loginUser, NsUser target) {
        if (!matchUserId(loginUser.getUserId())) {
            throw new UnAuthorizedException();
        }

        if (!matchPassword(target.getPassword())) {
            throw new UnAuthorizedException();
        }

        this.name = target.name;
        this.email = target.email;
    }

    private boolean matchUserId(String userId) {
        return this.userId.equals(userId);
    }

    public boolean matchPassword(String targetPassword) {
        return password.equals(targetPassword);
    }

    public boolean isGuestUser() {
        return false;
    }

    private static class GuestNsUser extends NsUser {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NsUser nsUser = (NsUser) o;
        return Objects.equals(id, nsUser.id) && Objects.equals(userId, nsUser.userId) && Objects.equals(password, nsUser.password) && Objects.equals(name, nsUser.name) && Objects.equals(email, nsUser.email) && Objects.equals(createdAt, nsUser.createdAt) && Objects.equals(updatedAt, nsUser.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, password, name, email, createdAt, updatedAt);
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

package nextstep.users.domain;

import nextstep.qna.exception.unchecked.UnAuthorizedException;

import java.time.LocalDateTime;
import java.util.Objects;

public class NsUser {
    public static final GuestNsUser GUEST_USER = new GuestNsUser();

    private Long id;

    private LogInPairKey logInPairKey;

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
        this.logInPairKey = new LogInPairKey(userId, password);
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void update(NsUser loginUser, NsUser target) {
        if (!target.matchLoginPairKey(this.logInPairKey)) {
            throw new UnAuthorizedException();
        }

        if (!loginUser.matchLoginPairKey(this.logInPairKey)) {
            throw new UnAuthorizedException();
        }

        this.name = target.name;
        this.email = target.email;
    }

    private boolean matchLoginPairKey(LogInPairKey targetKey) {
        return this.logInPairKey.equals(targetKey);
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
    public String toString() {
        return "NsUser{" +
                "id=" + id +
                ", userId='" + logInPairKey + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

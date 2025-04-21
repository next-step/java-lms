package nextstep.users.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.AuditInfo;

import java.time.LocalDateTime;
import java.util.Objects;

public class NsUser {
    public static final GuestNsUser GUEST_USER = new GuestNsUser();

    private Long id;

    private Long sessionId;

    private String userId;

    private String password;

    private String name;

    private String email;

    private AuditInfo auditInfo = new AuditInfo();

    public NsUser() {
    }

    public NsUser(Builder builder) {
        this.id = builder.id;
        this.sessionId = builder.sessionId;
        this.userId = builder.userId;
        this.password = builder.password;
        this.name = builder.name;
        this.email = builder.email;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public NsUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public NsUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public NsUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public NsUser setEmail(String email) {
        this.email = email;
        return this;
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

    public boolean matchUser(NsUser target) {
        return matchUserId(target.getUserId());
    }

    private boolean matchUserId(String userId) {
        return this.userId.equals(userId);
    }

    public boolean matchPassword(String targetPassword) {
        return password.equals(targetPassword);
    }

    public boolean equalsNameAndEmail(NsUser target) {
        if (Objects.isNull(target)) {
            return false;
        }

        return name.equals(target.name) &&
                email.equals(target.email);
    }

    public boolean isGuestUser() {
        return false;
    }

    @Override
    public String toString() {
        return "NsUser{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", auditInfo=" + auditInfo +
                '}';
    }

    public void enroll(Long id) {
        this.sessionId = id;
    }

    private static class GuestNsUser extends NsUser {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }

    public static class Builder {
        private Long id;
        private Long sessionId;
        private String userId;
        private String password;
        private String name;
        private String email;
        private AuditInfo auditInfo;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder sessionId(Long sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder auditInfo(LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.auditInfo = new AuditInfo(createdAt, updatedAt);
            return this;
        }

        public NsUser build() {
            return new NsUser(this);
        }
    }
}

package nextstep.users.domain;

import nextstep.users.exception.UnAuthorizedUserException;

import java.time.LocalDateTime;
import java.util.Objects;

public class NsUser {
    public static final GuestNsUser GUEST_USER = new GuestNsUser();

    private NsUserId userId;

    private UserCode userCode;

    private String password;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public NsUser(NsUserId userId, UserCode userCode, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.userCode = userCode;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static NsUser of(Long id, String userCode, String password, String name, String email) {
        return new NsUser(new NsUserId(id), new UserCode(userCode), password, name, email, LocalDateTime.now(), null);
    }

    public static NsUser of(Long userId, String userCode, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new NsUser(
                new NsUserId(userId),
                new UserCode(userCode),
                password,
                name,
                email,
                createdAt,
                updatedAt
        );
    }

    public NsUserId getUserId() {
        return this.userId;
    }

    public UserCode getUserCode() {
        return this.userCode;
    }

    public NsUser updateCode(String userCode) {
        this.userCode = new UserCode(userCode);
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

    public void update(UserCode userCode, NsUser target) {
        if (!matchUserId(userCode)) {
            throw new UnAuthorizedUserException();
        }

        if (!matchPassword(target.getPassword())) {
            throw new UnAuthorizedUserException();
        }

        this.name = target.name;
        this.email = target.email;
    }

    public boolean matchUser(UserCode userCode) {
        return this.userCode.equals(userCode);
    }

    private boolean matchUserId(UserCode userCode) {
        return this.userId.equals(userCode.value());
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
                "userId=" + userId +
                ", userCode=" + userCode +
                ", password='" + password + '\'' +
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
        NsUser other = (NsUser) o;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId.value());
    }

    private static class GuestNsUser extends NsUser {
        public GuestNsUser() {
            super(new NsUserId(0L), new UserCode("GUEST"), "password", "GUEST", "GUEST@GUEST.com", LocalDateTime.now(), LocalDateTime.now());
        }

        @Override
        public boolean isGuestUser() {
            return true;
        }
    }
}

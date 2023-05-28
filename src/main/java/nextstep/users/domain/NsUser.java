package nextstep.users.domain;

import nextstep.users.exception.UnAuthorizedUserException;
import nextstep.utils.KeyMakerRandomString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class NsUser {
    public static final GuestNsUser GUEST_USER = new GuestNsUser();

    @NotEmpty
    private UserCode userCode;

    @NotNull
    private String password;

    @NotNull
    private String name;

    private String email;

    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private LocalDateTime updatedAt;

    public NsUser(UserCode userCode, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userCode = userCode;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static NsUser of(String userCode, String password, String name, String email) {
        return new NsUser(new UserCode(userCode), password, name, email, LocalDateTime.now(), LocalDateTime.now());
    }

    public static NsUser of(String password, String name, String email) {
        return new NsUser(UserCode.any(KeyMakerRandomString.of()), password, name, email, LocalDateTime.now(), null);
    }

    public static NsUser of(String userCode, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new NsUser(
                new UserCode(userCode),
                password,
                name,
                email,
                createdAt,
                updatedAt
        );
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
        return this.userCode.value().equals(userCode.value());
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
                "userCode=" + userCode +
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
        return Objects.hash(userCode.value());
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    private static class GuestNsUser extends NsUser {
        public GuestNsUser() {
            super(new UserCode("GUEST"), "password", "GUEST", "GUEST@GUEST.com", LocalDateTime.now(), LocalDateTime.now());
        }

        @Override
        public boolean isGuestUser() {
            return true;
        }
    }
}

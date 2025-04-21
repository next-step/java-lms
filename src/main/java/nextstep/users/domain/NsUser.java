package nextstep.users.domain;

import nextstep.qna.UnAuthorizedException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

import static nextstep.courses.domain.model.Timestamped.toLocalDateTime;

public class NsUser {
    public static final GuestNsUser GUEST_USER = new GuestNsUser();

    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    private BigDecimal balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private NsUser() {
    }

    public NsUser(Long id, String userId, String password, String name, String email) {
        this(id, userId, password, name, email, LocalDateTime.now(), LocalDateTime.now());
    }

    public NsUser(Long id, String userId, String password, String name, BigDecimal balance) {
        this(id, userId, password, name, null, balance, LocalDateTime.now(), LocalDateTime.now());
    }

    public NsUser(Long id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, userId, password, name, email, new BigDecimal(0), createdAt, updatedAt);
    }

    public NsUser(Long id, String userId, String password, String name, String email, BigDecimal balance, Timestamp createdAt, Timestamp updatedAt) {
        this(id, userId, password, name, email, balance, toLocalDateTime(createdAt), toLocalDateTime(updatedAt));
    }

    public NsUser(Long id, String userId, String password, String name, String email, BigDecimal balance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
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

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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

    public synchronized void pay(Long price) {
        if (balance.compareTo(BigDecimal.valueOf(price)) < 0) {
            throw new IllegalArgumentException("not enough money");
        }
        balance = balance.subtract(BigDecimal.valueOf(price));
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
        if (o == null || getClass() != o.getClass()) return false;
        NsUser nsUser = (NsUser) o;
        return Objects.equals(id, nsUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
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

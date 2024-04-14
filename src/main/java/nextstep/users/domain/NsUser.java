package nextstep.users.domain;

import nextstep.qna.UnAuthorizedException;

import java.time.LocalDateTime;
import java.util.Objects;

public class NsUser {
    public static final GuestNsUser GUEST_USER = new GuestNsUser();

    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int chargePoint = 0;

    public void chargePoint(int chargePoint) {
        this.chargePoint += chargePoint;
    }

    public void minusPoint(int chargePoint) {
        String errorMessage = "가지고 있는 포인트보다 많습니다.";

        if (this.chargePoint < chargePoint) {
            throw new IllegalArgumentException(errorMessage);
        }

        this.chargePoint -= chargePoint;
    }

    public int getChargePoint() {
        return chargePoint;
    }

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
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

package nextstep.users.domain;

import nextstep.common.BaseEntity;
import nextstep.qna.UnAuthorizedException;

import java.time.LocalDateTime;
import java.util.Objects;

public class NsUser extends BaseEntity {
    public static final GuestNsUser GUEST_USER = new GuestNsUser();
    private String userId;

    private String password;

    private String name;

    private String email;

    private UserCourseType userCourseType;


    public NsUser() {
    }

    public NsUser(Long id, String userId, String password, String name, String email, UserCourseType userCourseType) {
        this(id, userId, password, name, email, userCourseType, LocalDateTime.now(), null);
    }

    public NsUser(Long id, String userId, String password, String name, String email, UserCourseType userCourseType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.userCourseType = userCourseType;
    }

    public Long getId() {
        return super.getId();
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
}

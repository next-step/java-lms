package nextstep.users.domain;

import nextstep.courses.domain.session.SessionType;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.exception.NoSuchUserException;

import java.time.LocalDateTime;
import java.util.Objects;

public class NsUser {
    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    private UserType type;

    private UserSessionType sessionType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public NsUser() {
    }

    public NsUser(Long id, String userId, String password, String name, String email,
                  UserType type, UserSessionType sessionType) {
        this(id, userId, password, name, email, type, sessionType, LocalDateTime.now(), null);
    }

    public NsUser(Long id, String userId, String password, String name, String email,
                  UserType type, UserSessionType sessionType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
        this.sessionType = sessionType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean isSameSessionType(SessionType sessionType) {
        return this.sessionType.isSameType(sessionType);
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
}

package nextstep.users.domain;

import java.time.LocalDateTime;

public final class NsUserBuilder {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private NsUserBuilder() {
    }

    public static NsUserBuilder aNsUser() {
        return new NsUserBuilder();
    }

    public NsUserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public NsUserBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public NsUserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public NsUserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public NsUserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public NsUserBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public NsUserBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public NsUser build() {
        return new NsUser(id, userId, password, name, email, createdAt, updatedAt);
    }
}

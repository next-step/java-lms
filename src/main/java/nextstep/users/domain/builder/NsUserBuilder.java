package nextstep.users.domain.builder;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class NsUserBuilder {
    private Long id = 1L;
    private String userId = "javajigi";
    private String password = "test";
    private String name = "자바지기";
    private String email = "javajigi@slipp.net";
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

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

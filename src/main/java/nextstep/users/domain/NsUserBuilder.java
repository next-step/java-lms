package nextstep.users.domain;

import java.time.LocalDateTime;

public class NsUserBuilder {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private NsUserBuilder() {
    }

    public static NsUserBuilder init() {
        return new NsUserBuilder();
    }

    public NsUserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public NsUserBuilder userId(String userId) {
        this.userId = userId;
        return this;
    }

    public NsUserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public NsUserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public NsUserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public NsUserBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public NsUserBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public NsUser build() {
        return new NsUser(id, userId, password, name, email, createdAt, updatedAt);
    }

}

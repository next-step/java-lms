package nextstep.users.domain;

import nextstep.common.domain.BaseEntity;
import nextstep.qna.exception.unchecked.UnAuthorizedException;

import java.time.LocalDateTime;
import java.util.Objects;

public class NsUser extends BaseEntity {
    private LogInPairKey logInPairKey;

    private String name;

    private String email;

    public NsUser(Long id, String userId, String password, String name, String email) {
        this(id, userId, password, name, email, LocalDateTime.now(), null);
    }

    public NsUser(Long id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.logInPairKey = new LogInPairKey(userId, password);
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public void update(NsUser loginUser, NsUser target) {
        if (!target.matchLoginPairKey(this.logInPairKey)) {
            throw new UnAuthorizedException();
        }

        if (!loginUser.matchLoginPairKey(this.logInPairKey)) {
            throw new UnAuthorizedException();
        }

        this.name = target.name;
        this.email = target.email;
    }

    private boolean matchLoginPairKey(LogInPairKey targetKey) {
        return this.logInPairKey.equals(targetKey);
    }

    @Override
    public String toString() {
        return "NsUser{" +
                "id=" + getId() +
                ", userId='" + logInPairKey + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package nextstep.users.domain;

import java.util.Objects;

public class LogInPairKey {
    private String userId;
    private String password;

    public LogInPairKey(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public boolean matchUser(String targetUserId, String targetPassword) {
        return matchUserId(targetUserId) && matchPassword(targetPassword);
    }

    private boolean matchUserId(String targetUserId) {
        return this.userId.equals(targetUserId);
    }

    private boolean matchPassword(String targetPassword) {
        return password.equals(targetPassword);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogInPairKey that = (LogInPairKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(password,
                that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, password);
    }

    @Override
    public String toString() {
        return "LogInPairKey{" +
                "userId='" + userId + '\'' +
                '}';
    }
}

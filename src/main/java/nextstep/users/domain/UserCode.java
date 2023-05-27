package nextstep.users.domain;

import nextstep.utils.DomainCode;

import java.util.Objects;

public class UserCode implements DomainCode {
    private final String userCode;

    public UserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String value() {
        return this.userCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCode other = (UserCode) o;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCode);
    }

    @Override
    public String toString() {
        return "UserCode{" +
                "userCode='" + userCode + '\'' +
                '}';
    }
}

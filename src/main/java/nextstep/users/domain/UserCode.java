package nextstep.users.domain;

import nextstep.users.exception.UserCodeException;
import nextstep.utils.DomainCode;
import nextstep.utils.PrimaryKeyCodeMaker;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class UserCode implements DomainCode {
    @NotEmpty
    private final String userCode;

    public UserCode(String userCode) {
        validateCode(userCode);
        this.userCode = userCode;
    }

    public static UserCode of(String userCode) {
        return new UserCode(userCode);
    }

    public static UserCode any(PrimaryKeyCodeMaker primaryKeyCodeMaker) {
        return new UserCode(primaryKeyCodeMaker.generate());
    }

    private void validateCode(String userCode) {
        if (userCode.isBlank() || userCode.isEmpty()) {
            throw new UserCodeException();
        }
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

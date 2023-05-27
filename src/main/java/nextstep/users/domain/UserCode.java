package nextstep.users.domain;

public class UserCode {
    private final String userCode;

    public UserCode(String userCode) {
        this.userCode = userCode;
    }

    public String value() {
        return this.userCode;
    }
}

package nextstep.users.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$");
    private static final String VALIDATION_PASSWORD_EXCEPTION = "유효하지 않은 비밀번호 입니다.";
    String password;


    // 최소 8자 이상의 길이여야 합니다.
    // 적어도 하나의 숫자(0-9)를 포함해야 합니다.
    // 적어도 하나의 소문자 알파벳(a-z)을 포함해야 합니다.
    // 적어도 하나의 대문자 알파벳(A-Z)을 포함해야 합니다.
    // 적어도 하나의 특수문자(@#$%^&+=)를 포함해야 합니다.
    // 공백은 허용되지 않습니다.
    public Password(String password) {
        validatePassword(password);
        this.password = password;
    }

    public void changePassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    public void validatePassword(String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(VALIDATION_PASSWORD_EXCEPTION);
        }
    }

    public String getPassword() {
        return password;
    }
}

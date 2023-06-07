package nextstep.users.domain;

import java.util.regex.Pattern;

public class Email {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern DOMAIN_PATTERN = Pattern.compile(".*\\.(com|co\\.kr)$");
    private static final int SPLIT_MAIL_LENGTH = 2;
    private static final String EMAIL_DELIMITER = "@";
    private static final String NOT_EMAIL_FORMAT = "올바른 이메일 형식이 아닙니다.";
    private String email;

    public Email(String email) {
        if (!checkEmail(email)) {
            throw new IllegalArgumentException(NOT_EMAIL_FORMAT);
        }
        this.email = email;
    }

    public boolean checkEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }

        String[] splitEmail = email.split(EMAIL_DELIMITER);
        if (splitEmail.length != SPLIT_MAIL_LENGTH) {
            return false;
        }

        String domain = splitEmail[1];
        return DOMAIN_PATTERN.matcher(domain).matches();
    }

    public String getEmail() {
        return email;
    }
}
